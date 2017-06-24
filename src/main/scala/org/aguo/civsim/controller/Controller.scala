package org.aguo.civsim.controller

import org.aguo.civsim.model.World
import org.aguo.civsim.view._

import scala.io.StdIn._

object Controller {

  def run(world: World): World = {
    run(handleInput(readLine(), world))
  }

  def handleInput(input: String, world: World): World = input match {
    case "help" => HelpView.render(world)
    case "quit" => System.exit(0); world // TODO: exit more gracefully?
    case _ => UnknownView.render(world)
  }

}
