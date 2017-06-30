package org.aguo.civsim.controller

import org.aguo.civsim.model.World
import org.aguo.civsim.view._

import scala.io.StdIn._

object MainController {

  val HelpCommand = "help (.*)".r
  val ViewCommand = "view (.*)".r
  val ExamineCommand = "examine (.*)".r
  val JobsCommand = "jobs (.*)".r

  def run(world: World): World = {
    run(handleInput(readLine(), world))
  }

  def handleInput(input: String, world: World): World = input match {
    case ExamineCommand(s) => ExamineController.handleInput(s, world)
    case "help" => MainHelpScreen.render(world)
    case HelpCommand(s) => HelpController.handleInput(s, world)
    case "jobs" => ViewController.handleInput("jobs", world)
    case JobsCommand(s) => JobsController.handleInput(s, world)
    case "next" => world.nextTurn()
    case "quit" => System.exit(0); world // TODO: exit more gracefully?
    case "summary" => SummaryScreen.render(world)
    case ViewCommand(s) => ViewController.handleInput(s, world)
    case _ => UnknownScreen.render(world)
  }

}
