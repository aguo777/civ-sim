package org.aguo.civsim.model.domain

trait Building {
  val size: BigInt
  val capacity: BigInt
  val productionCost: BigInt
}

case object Farm extends Building {
  override val size: BigInt = 1
  override val capacity: BigInt = 10
  override val productionCost: BigInt = 5
}

trait Housing extends Building

case object Hut extends Housing {
  override val size: BigInt = 1
  override val capacity: BigInt = 2
  override val productionCost: BigInt = 1
}
