package daos

import domain.entities.ArticleEntity

import scala.concurrent._

import play.api.libs.concurrent.Execution.Implicits._

class ArticlesDAO() extends DAO[ArticleEntity] {
    def save(article : ArticleEntity) : Future[ArticleEntity] = Future {
        new ArticleEntity()
    }
}
