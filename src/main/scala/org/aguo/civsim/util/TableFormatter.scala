package org.aguo.civsim.util

object TableFormatter {
  def formatTable(
      title: String,
      rows: Traversable[(String, String)],
      leftColor: String = Console.RESET,
      rightColor: String = Console.RESET,
      margin: Int = 4
  ): String = {
    val leftWidth = rows.map(_._1.length).max
    val rightWidth = rows.map(_._2.length).max
    s"${Console.BOLD}${Console.WHITE}$title${Console.RESET}\n\n" +
        (rows.map {
          case (left, right) =>
            s"$leftColor${leftPad(left, leftWidth + margin)}${Console.RESET}   $rightColor${rightPad(right, rightWidth)}${Console.RESET}"
        } mkString("\n"))
  }

  private def leftPad(s: String, desiredWidth: Int): String = {
    if (desiredWidth < s.length) {
      s
    } else {
      " " * (desiredWidth - s.length) + s
    }
  }

  private def rightPad(s: String, desiredWidth: Int): String = {
    if (desiredWidth < s.length) {
      s
    } else {
      s + " " * (desiredWidth - s.length)
    }
  }
}
