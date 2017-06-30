package org.aguo.civsim.model.domain

case class Territory(
    totalLand: BigDecimal,
    buildings: Map[Building, BigInt]
) {

}

object Territory {
  def New: Territory = Territory(
    totalLand = 10,
    buildings = Map(
      Hut -> 10,
      Farm -> 1
    )
  )
}
