package domain.factories

import domain.entities.ArticleEntity

import play.api.libs.json._

class ArticlesFactory() extends Factory[ArticleEntity] {
    // create new article

    override def build(data: JsObject): Option[ArticleEntity] = {
        return Some(new ArticleEntity((data \ "title").as[String], (data \ "content").as[String]))
    }
}
