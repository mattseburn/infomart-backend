package domain.valueobjects

trait ValueObject[T <: ValueObject[T]] {
    def isEqual(other : T) : Boolean
}
