package domain.entities

class ArticleEntity(title_c: String, content_c: String, id: Option[Long]) extends Entity[ArticleEntity] {
    val title: String = title_c
    val content: String = content_c

    def isEqual(other: ArticleEntity): Boolean = {
        false
    }
}
