package domain.factories

import domain.entities.Entity

trait Factory[T <: Entity[T]] {
    def build(data : Map[String, String]) : T
}
