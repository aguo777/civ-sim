package org.aguo.civsim.model.domain

import org.aguo.civsim.model.World

import Console._

trait Building {
  val size: BigInt
  val capacity: BigInt
  val productionCost: BigInt
  def render: String = s"$GREEN$toString$RESET"
  def examine(world: World): String
}

object Building {
  def unapply(arg: String): Option[Building] = arg match {
    case "farm" => Some(Farm)
    case "hut" => Some(Hut)
    case _ => None
  }
}

trait JobBuilding extends Building {
  val job: Job

  override def examine(world: World): String = {
    s"$render supports $YELLOW$capacity$RESET ${job.render}s, occupies $YELLOW$size$RESET space, and costs $RED$productionCost$RESET to build."
  }
}

case object Farm extends JobBuilding {
  override val size: BigInt = 1
  override val capacity: BigInt = 10
  override val productionCost: BigInt = 5
  override val job: Job = Farmer
}

trait Housing extends Building {
  override def examine(world: World): String = {
    s"$render houses $YELLOW$capacity$RESET people, occupies $YELLOW$size$RESET space, and costs $RED$productionCost$RESET to build."
  }
}

case object Hut extends Housing {
  override val size: BigInt = 1
  override val capacity: BigInt = 2
  override val productionCost: BigInt = 1
}
