package domain.repositories

import daos.ArticlesDAO
import domain.entities.ArticleEntity

import scala.concurrent._
import scala.concurrent.duration.Duration
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Success, Failure}

class ArticlesRepository() extends Repository[ArticleEntity] {
    val dao = new ArticlesDAO()

    override def add(article: ArticleEntity): Future[Option[Long]] = {
        // add article to db
        dao.save(article)
    }

    override def retrieve(): Future[Option[List[ArticleEntity]]] = {
        dao.get()
    }
}
