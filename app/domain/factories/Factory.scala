package domain.factories

import play.api.libs.json._

trait Factory[T] {
    def build(data: JsObject): Option[T]
}
