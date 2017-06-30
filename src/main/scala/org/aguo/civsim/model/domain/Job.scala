package org.aguo.civsim.model.domain

import org.aguo.civsim.model._

import scala.math.BigDecimal.RoundingMode
import Console._

sealed trait Job {
  def render: String = s"${YELLOW}${toString}${RESET}"
  def examine(world: World): String
}

object Job {
  def unapply(string: String): Option[Job] = string match {
    case "farmer" => Some(Farmer)
    case "builder" => Some(Builder)
    case _ => None
  }
}

sealed trait FoodJob extends Job {
  def foodProduction(world: World): BigDecimal

  override def examine(world: World): String = {
    s"$render produces ${GREEN}${foodProduction(world).setScale(2, RoundingMode.HALF_UP)} food${RESET}."
  }
}

case object Farmer extends FoodJob {
  override def foodProduction(world: World): BigDecimal = {
    1.2
  }
}

trait ProductionJob extends Job {
  def production(world: World): BigDecimal

  override def examine(world: World): String = {
    s"$render has ${RED}${production(world).setScale(2, RoundingMode.HALF_UP)} production${RESET}."
  }
}

case object Builder extends ProductionJob {
  override def production(world: World): BigDecimal = {
    1.0
  }
}
