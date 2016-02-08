package domain.repositories

import daos.ArticlesDAO
import domain.entities.ArticleEntity

import scala.concurrent._
import scala.concurrent.duration.Duration
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Success, Failure}

class ArticlesRepository() extends Repository[ArticleEntity] {
    val dao = new ArticlesDAO()

    override def add(article: ArticleEntity): Future[Option[ArticleEntity]] = {
        // add article to db
        // create & return new ArticleEntity with returned id

        val result = dao.save(article) map { r =>
            //Option(new ArticleEntity("title", "content", None))
            //println(r)
        }

        Await.ready(result, Duration.Inf)
        Future(Option(new ArticleEntity("title", "content", None)))
    }
}
