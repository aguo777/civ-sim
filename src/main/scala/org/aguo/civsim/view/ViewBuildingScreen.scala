package org.aguo.civsim.view

import org.aguo.civsim.model.World
import org.aguo.civsim.util.TableFormatter._

object ViewBuildingScreen extends ClearingScreen {

  override protected def generateMessage(world: World): String = {
    formatTable(
      title = "Buildings:",
      rows = world.domain.territory.buildings.map {
        case (building, count) => building.toString -> count.toString
      },
      leftColor = Console.GREEN,
      rightColor = Console.YELLOW
    )
  }
}
