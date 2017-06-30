package org.aguo.civsim.view
import org.aguo.civsim.model.World

case class ErrorScreen(e: Throwable) extends Screen {
  override def generateMessage(world: World): String = s"${Console.RED}Error:${Console.RESET} ${e.getMessage}"
}
