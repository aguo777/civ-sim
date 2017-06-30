package org.aguo.civsim.view

import org.aguo.civsim.model.World

import scala.sys.process._

/**
  * Display text to player.
  */
trait Screen {
  protected def generateMessage(world: World): String

  def render(world: World): World = {
    println(generateMessage(world))
    println("")
    println("Your people await your command. Type 'help' to see a list of options.")
    print("Your command: ")
    world
  }
}

/**
  * Clears the shell.
  */
trait ClearingScreen extends Screen {
  override def render(world: World): World = {
    "clear".!
    super.render(world)
  }
}


