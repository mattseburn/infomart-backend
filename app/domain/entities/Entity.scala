package domain.entities

import play.api.libs.json._

trait Entity[T <: Entity[T]] {
    def isEqual(other : T): Boolean
    def toJson(): JsObject
}
