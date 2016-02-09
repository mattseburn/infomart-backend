package daos

import domain.entities.ArticleEntity

import scala.concurrent._

import anorm._
import play.api.db.DB
import play.api.libs.concurrent.Execution.Implicits._
import play.api.Play.current

import domain.entities.ArticleEntity

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

    def get(): Future[Option[List[ArticleEntity]]] = {
        var articles: List[ArticleEntity] = Nil

        DB.withConnection { implicit c =>
            val rowParser: RowParser[ArticleEntity] = (
                SqlParser.long("id") ~
                SqlParser.str("title") ~
                SqlParser.str("content")
            ) map {
                case id ~ title ~ content => new ArticleEntity(title, content, Some(id))
            }
            val parser: ResultSetParser[List[ArticleEntity]] = rowParser.*
            val results: List[ArticleEntity] = SQL"""
                SELECT * FROM articles
                """.as(parser)
                articles = results
        }

        if (articles == Nil) Future(None) else Future(Some(articles))
    }
}
