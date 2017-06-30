package org.aguo.civsim.controller

import org.aguo.civsim.model.World
import org.aguo.civsim.model.domain._
import org.aguo.civsim.view._

import scala.util.{ Failure, Success }

object JobsController {

  val SetCommand = """(\d+) (.*)""".r
  val AddCommand = """\+(\d+) (.*)""".r
  val SubtractCommand = """-(\d+) (.*)""".r
  val MaxCommand = """max (.*)""".r
  val ClearCommand = """clear (.*)""".r
  val MoveCommand = """(\d+) (.*) to (.*)""".r

  def handleInput(input: String, world: World): World = {
    val domain = world.domain
    val population = domain.population
    val jobs = population.jobAllocations.keySet
    val newPop = input match {
      case SetCommand(n, Job(job)) if jobs.contains(job) => population.setJobAllocation(job, BigInt(n))
      case AddCommand(n, Job(job)) if jobs.contains(job) => population.addJobAllocation(job, BigInt(n))
      case SubtractCommand(n, Job(job)) if jobs.contains(job) => population.addJobAllocation(job, -BigInt(n))
      case MaxCommand(Job(job)) if jobs.contains(job) => population.maxJobAllocation(job)
      case ClearCommand(Job(job)) if jobs.contains(job) => population.clearJobAllocation(job)
      case MoveCommand(n, Job(from), Job(to)) if jobs.contains(from) && jobs.contains(to) => population.moveJobAllocation(from, to, BigInt(n))
      case _ => Failure(new IllegalArgumentException("Invalid command."))
    }
    newPop match {
      case Success(newPopulation) =>
        ViewJobScreen.render(world.copy(domain = domain.copy(population = newPopulation)))
      case Failure(e) => ErrorScreen(e).render(world)
    }
  }
}
