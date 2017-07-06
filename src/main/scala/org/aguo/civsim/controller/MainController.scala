package org.aguo.civsim.controller

import org.aguo.civsim.model.World
import org.aguo.civsim.view._

import scala.io.StdIn._

object MainController {

  val HelpCommand = "[h|help] (.*)".r
  val ViewCommand = "[v|view] (.*)".r
  val ExamineCommand = "[e|examine] (.*)".r
  val JobsCommand = "[j|jobs] (.*)".r

  def run(world: World): World = {
    run(handleInput(readLine(), world))
  }

  def handleInput(input: String, world: World): World = input match {
    case ExamineCommand(s) => ExamineController.handleInput(s, world)
    case "h" | "help" => MainHelpScreen.render(world)
    case HelpCommand(s) => HelpController.handleInput(s, world)
    case "j" | "jobs" => ViewController.handleInput("jobs", world)
    case JobsCommand(s) => JobsController.handleInput(s, world)
    case "n" | "next" => world.nextTurn()
    case "q" | "quit" => System.exit(0); world // TODO: exit more gracefully?
    case "s" | "summary" => SummaryScreen.render(world)
    case ViewCommand(s) => ViewController.handleInput(s, world)
    case _ => UnknownScreen.render(world)
  }

}
