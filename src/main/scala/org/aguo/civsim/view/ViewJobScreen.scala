package org.aguo.civsim.view

import org.aguo.civsim.model.World
import org.aguo.civsim.util.TableFormatter._

object ViewJobScreen extends ClearingScreen {

  override protected def generateMessage(world: World): String = {
    val population = world.domain.population
    val jobAllocations = population.jobAllocations
    val unemployed = population.unemployed
    formatTable(
      title = "Job Allocations:",
      rows = jobAllocations.map {
        case (job, count) => job.toString -> count.toString
      } ++ Map("Unemployed" -> unemployed.toString),
      leftColor = Console.GREEN,
      rightColor = Console.YELLOW
    )
  }
}
