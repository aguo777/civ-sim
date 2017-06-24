package org.aguo.civsim.view

import org.aguo.civsim.model.World

import scala.sys.process._

trait View {
  def generateMessage(world: World): String

  def render(world: World): World = {
    println(generateMessage(world))
    world
  }
}

trait ClearingView extends View {
  override def render(world: World): World = {
    "clear".!
    super.render(world)
  }
}


