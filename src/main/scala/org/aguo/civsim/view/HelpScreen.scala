package org.aguo.civsim.view

import org.aguo.civsim.model.World
import org.aguo.civsim.util.TableFormatter.formatTable

trait HelpScreen extends ClearingScreen {
  protected def getAvailableCommands(world: World): List[(String, String)]

  final override protected def generateMessage(world: World): String = {
    formatTable(
      title = "Available commands:",
      rows = getAvailableCommands(world),
      leftColor = Console.GREEN,
      rightColor = Console.WHITE
    )
  }
}

object MainHelpScreen extends HelpScreen {
  override protected def getAvailableCommands(world: World): List[(String, String)] = List(
    "help" -> "Show this list of commands.",
    "help <command>" -> "Shows list of further options for <command>",
    "summary" -> "Show a summary of your domain.",
    "view <screen>" -> "View more information on <screen>. Type 'help view' for options.",
    "jobs" -> "View jobs. Same as 'view jobs'.",
    "jobs <job_command>" -> "Reallocate jobs. Type 'help jobs' for options.",
    "examine <entity>" -> "Examine <entity>. Type 'help examine' for options.",
    "next" -> "End the current turn.",
    "quit" -> "Quit the game."
  )
}

object HelpViewScreen extends HelpScreen {
  override protected def getAvailableCommands(world: World): List[(String, String)] = List(
    "view jobs" -> "View job allocation of the population."
  )
}

object HelpExamineScreen extends HelpScreen {
  override def getAvailableCommands(world: World): List[(String, String)] = List(
    "examine <job>" -> "Examine details of people in <job>. For example, 'examine farmer'."
  )
}

object HelpJobsScreen extends HelpScreen {
  override protected def getAvailableCommands(world: World): List[(String, String)] = List(
    "jobs <number> <job>" -> "Assign <number> people to <job>. For example, 'jobs 5 farmer'.",
    "jobs +<number> <job>" -> "Adds <number> people to <job>. For example, 'jobs +2 farmer'.",
    "jobs -<number> <job>" -> "Removes <number> people from <job>. For example, 'jobs -2 farmer'.",
    "jobs max <job>" -> "Assigns all unemployed people to <job>. For example, 'jobs max farmer'.",
    "jobs clear <job>" -> "Removes all people from <job>. For example, 'jobs clear farmer'.",
    "jobs <number> <job1> to <job2>" -> "Moves <number> people from <job1> to <job2>. For exampl,e 'jobs 2 farmer to builder'."
  )
}
