package domain.entities

class ArticleEntity() extends Entity[ArticleEntity] {
    val title = "title"
    val content = "content"

    def isEqual(other: ArticleEntity): Boolean = {
        false
    }
}
