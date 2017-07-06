package org.aguo.civsim.controller

import org.aguo.civsim.model.World
import org.aguo.civsim.model.domain._
import org.aguo.civsim.view._

object ExamineController {
  def handleInput(input: String, world: World): World = {
    val domain = world.domain
    val population = domain.population
    val territory = domain.territory
    val jobs = population.jobAllocations.keySet
    val buildings = territory.buildings
    input match {
      case Building(building) if buildings.contains(building) => ExamineBuildingScreen(building).render(world)
      case Job(job) if jobs.contains(job) => ExamineJobScreen(job).render(world)
      case _ => UnknownScreen.render(world)
    }
  }
}
