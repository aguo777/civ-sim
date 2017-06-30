package org.aguo.civsim.model.domain

import org.aguo.civsim.model.World

import scala.util.{ Failure, Success, Try }

case class Population(
    value: BigDecimal,
    jobAllocations: Map[Job, BigInt]
) {
  val count: BigInt = value.toBigInt
  val unemployed: BigInt = count - jobAllocations.values.sum
  require(jobAllocations.values.forall(_ >= 0))
  require(unemployed >= 0)

  def setJobAllocation(job: Job, newAllocation: BigInt): Try[Population] = {
    val currentAllocation = jobAllocations.getOrElse(job, BigInt(0))
    val delta = newAllocation - currentAllocation
    if (newAllocation < 0) {
      Failure(new IllegalArgumentException(s"Must allocate positive number to ${job}."))
    } else if (delta > unemployed) {
      Failure(new IllegalArgumentException(s"Insufficient people to allocate to ${job}."))
    } else {
      val newJobAllocations = jobAllocations.updated(job, newAllocation)
      val newPopulation = copy(jobAllocations = newJobAllocations)
      Success(newPopulation)
    }
  }

  def addJobAllocation(job: Job, additional: BigInt): Try[Population] = {
    val currentAllocation = jobAllocations.getOrElse(job, BigInt(0))
    val newAllocation = currentAllocation + additional
    setJobAllocation(job, newAllocation)
  }

  def maxJobAllocation(job: Job): Try[Population] = {
    addJobAllocation(job, unemployed)
  }

  def clearJobAllocation(job: Job): Try[Population] = {
    setJobAllocation(job, BigInt(0))
  }

  def moveJobAllocation(from: Job, to: Job, number: BigInt): Try[Population] = {
    this.addJobAllocation(from, -number).flatMap(_.addJobAllocation(to, number))
  }

  def updateValue(newValue: BigDecimal): Population = {
    val employed = count - unemployed
    val newJobAllocations = if (newValue.toBigInt < employed) {
      // Population decreased below current working population
      // TODO: More sophisticated
      // For now, just decrease jobs proportionally
      val fraction = newValue / BigDecimal(employed)
      jobAllocations.mapValues(n => (BigDecimal(n) * fraction).toBigInt)
    } else jobAllocations
    copy(value = newValue, jobAllocations = newJobAllocations)
  }

  def resolveFood(world: World): Population = {
    val foodProduction = jobAllocations.collect {
      case (fj: FoodJob, n) => fj.foodProduction(world) * BigDecimal(n)
    } sum
    val foodConsumption = BigDecimal(count)
    val foodSurplus = foodProduction - foodConsumption
    val growth = foodSurplus
    val newValue = value + growth
    updateValue(newValue)
  }

  def resolveJobs(focus: DomainArea): Population = focus match {
    case Food => maxJobAllocation(Farmer).get // TODO: More sophisticated
    case _ => this
  }

}

object Population {
  def New: Population = Population(
    value = 10,
    jobAllocations = Map(
      Farmer -> 10,
      Builder -> 0
    )
  )
}
