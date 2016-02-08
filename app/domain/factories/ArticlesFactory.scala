package domain.factories

import domain.entities.ArticleEntity

import play.api.libs.json._

class ArticlesFactory() extends Factory[ArticleEntity] {
    // create new article

    override def build(data: JsObject): ArticleEntity = {
        val title = (data \ "title").as[String]
        val content = (data \ "content").as[String]
        val id: Option[Long] = if (data.keys("id")) Option((data \ "id").as[Long]) else None;
        return new ArticleEntity(title, content, id)
    }
}
