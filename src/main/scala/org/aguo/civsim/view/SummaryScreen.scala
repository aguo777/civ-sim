package org.aguo.civsim.view

import org.aguo.civsim.model.World

object SummaryScreen extends ClearingScreen {

  private def timeSummary(world: World): String = {
    s"""${Console.BOLD}Turn ${world.turnIndex}${Console.RESET}"""
  }

  private def populationSummary(world: World): String = {
    val population = world.domain.population
    s"""You have ${Console.CYAN}${population.count}${Console.RESET} people."""
  }

  override protected def generateMessage(world: World): String = {
    s"""${timeSummary(world)}: ${populationSummary(world)}"""
  }
}
