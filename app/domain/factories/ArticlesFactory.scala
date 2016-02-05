package domain.factories

import domain.entities.ArticleEntity

class ArticlesFactory() {
    // create new article

    def create(title : String, content : String) : ArticleEntity = {
        return new ArticleEntity()
    }
}
