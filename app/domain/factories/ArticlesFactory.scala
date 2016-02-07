package domain.factories

import domain.entities.ArticleEntity

class ArticlesFactory() extends Factory[ArticleEntity] {
    // create new article

    override def build(data : Map[String, String]) : ArticleEntity = {
        return new ArticleEntity("title", "content")
    }
}
