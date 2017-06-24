package org.aguo.civsim.view

import org.aguo.civsim.model.World

object HelpView extends ClearingView {
  override def generateMessage(world: World): String = {
    """Available commands:"""
  }
}
