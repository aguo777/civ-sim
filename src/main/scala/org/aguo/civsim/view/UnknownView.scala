package org.aguo.civsim.view

import org.aguo.civsim.model.World

object UnknownView extends View {
  override def generateMessage(world: World): String = """Invalid command."""
}
