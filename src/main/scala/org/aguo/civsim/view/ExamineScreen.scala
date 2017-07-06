package org.aguo.civsim.view
import org.aguo.civsim.model.World
import org.aguo.civsim.model.domain.{ Building, Job }

trait ExamineScreen extends ClearingScreen

case class ExamineJobScreen(job: Job) extends ExamineScreen {
  override def generateMessage(world: World): String = job.examine(world)
}

case class ExamineBuildingScreen(building: Building) extends ExamineScreen {
  override def generateMessage(world: World): String = building.examine(world)
}
