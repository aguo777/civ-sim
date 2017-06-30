package org.aguo.civsim.model.domain

import org.aguo.civsim.model.World

case class Domain(
    population: Population,
    territory: Territory,
    focus: DomainArea = Food
) {

  def resolveFood(world: World): Domain = {
    copy(population = population.resolveFood(world))
  }

  def resolveJobs: Domain = {
    copy(population = population.resolveJobs(focus))
  }
}

object Domain {
  def New: Domain = Domain(
    population = Population.New,
    territory = Territory.New
  )
}
