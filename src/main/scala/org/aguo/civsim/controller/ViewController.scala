package org.aguo.civsim.controller

import org.aguo.civsim.model.World
import org.aguo.civsim.view._

object ViewController {
  def handleInput(input: String, world: World): World = input match {
    case "b" |  "buildings" => ViewBuildingScreen.render(world)
    case "j" | "jobs" => ViewJobScreen.render(world)
    case _ => UnknownScreen.render(world)
  }
}
