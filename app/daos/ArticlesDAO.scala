package daos

import domain.entities.ArticleEntity

import scala.concurrent._

import anorm._
import play.api.db.DB
import play.api.libs.concurrent.Execution.Implicits._
import play.api.Play.current

class ArticlesDAO() extends DAO[ArticleEntity] {
    def save(article: ArticleEntity): Future[Option[Long]] = Future {
        val title = article.title
        val content = article.content

        DB.withConnection { implicit c =>
            SQL"""
                INSERT INTO articles (title, content) VALUES($title, $content)
                """.executeInsert()
        }
    }
}
