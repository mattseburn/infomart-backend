package daos

import scala.concurrent._

trait DAO[T] {
    def save(item : T) : Future[Option[Long]]
}
