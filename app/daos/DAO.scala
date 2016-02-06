package daos

import scala.concurrent._

trait DAO[T] {
    def save(article : T) : Future[T]
}
