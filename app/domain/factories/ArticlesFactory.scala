package domain.factories

import domain.entities.ArticleEntity

case class ArticlesFactory() {
    // create new article

    def create(title : String, content : String) : ArticleEntity = {
        return ArticleEntity()
    }
}
