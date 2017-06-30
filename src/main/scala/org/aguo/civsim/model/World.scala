package org.aguo.civsim.model

import org.aguo.civsim.model.domain._
import org.aguo.civsim.view.SummaryScreen

case class World(
    domain: Domain,
    turnIndex: Int
) {
  def nextTurn(): World = {
    val newWorld = this
        .resolveFood
        .resolveJobs
        .incrementTurn
    SummaryScreen.render(newWorld)
    newWorld
  }

  private def incrementTurn: World = this.copy(turnIndex = turnIndex + 1)

  private def resolveFood: World = {
    copy(domain = domain.resolveFood(this))
  }

  private def resolveJobs: World = {
    copy(domain = domain.resolveJobs)
  }
}

object World {
  def New: World = World(
    domain = Domain.New,
    turnIndex = 0
  )
}
