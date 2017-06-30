package org.aguo.civsim.controller

import org.aguo.civsim.model.World
import org.aguo.civsim.view.{ ViewJobScreen, UnknownScreen }

object ViewController {
  def handleInput(input: String, world: World): World = input match {
    case "jobs" => ViewJobScreen.render(world); world
    case _ => UnknownScreen.render(world); world
  }
}
