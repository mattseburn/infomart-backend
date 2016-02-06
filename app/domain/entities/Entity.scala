package domain.entities

trait Entity[T <: Entity[T]] {
    def isEqual(other : T) : Boolean
}
