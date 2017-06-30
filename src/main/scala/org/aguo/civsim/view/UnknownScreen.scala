package org.aguo.civsim.view

import org.aguo.civsim.model.World

object UnknownScreen extends Screen {
  override protected def generateMessage(world: World): String = """Invalid command."""
}
