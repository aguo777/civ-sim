package org.aguo.civsim

import org.aguo.civsim.controller.MainController
import org.aguo.civsim.model.World
import org.aguo.civsim.view.SummaryScreen

object CivSim {
  def main(args: Array[String]): Unit = {
    // Initialize new world
    val world = World.New
    SummaryScreen.render(world)
    MainController.run(world)
  }
}
