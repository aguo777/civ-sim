package org.aguo.civsim

import org.aguo.civsim.controller.Controller
import org.aguo.civsim.model.World

object CivSim {
  def main(args: Array[String]): Unit = {
    // Initialize new world
    val world = World.New
    Controller.run(world)
  }
}
