package org.aguo.civsim.controller

import org.aguo.civsim.model.World
import org.aguo.civsim.view._

object HelpController {
  def handleInput(input: String, world: World): World = input match {
    case "view" => HelpViewScreen.render(world)
    case "examine" => HelpExamineScreen.render(world)
    case "jobs" => HelpJobsScreen.render(world)
    case _ => UnknownScreen.render(world)
  }
}
