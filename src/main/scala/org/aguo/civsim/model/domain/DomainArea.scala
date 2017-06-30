package org.aguo.civsim.model.domain

import org.aguo.civsim.model.World

sealed trait DomainArea
case object Food extends DomainArea {
  def resolve(world: World): World = {
    val domain = world.domain
    val population = domain.population
    val jobAllocations = population.jobAllocations
    val foodProduction = jobAllocations.collect {
      case (fj: FoodJob, n) => fj.foodProduction(world) * BigDecimal(n)
    } sum
    val foodConsumption = BigDecimal(population.count)
    val foodSurplus = foodProduction - foodConsumption
    val growth = foodSurplus
    val newPopulationValue = population.value + growth
    world.copy(domain = domain.copy(population = population.copy(value = newPopulationValue)))
  }
}
case object Production extends DomainArea
