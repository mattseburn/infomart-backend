package domain.repositories

import scala.concurrent._

trait Repository[T] {
    def add(item: T): Future[Option[Long]]
}
