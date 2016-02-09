package domain.entities

import play.api.libs.json._

class ArticleEntity(title_c: String, content_c: String, id_c: Option[Long]) extends Entity[ArticleEntity] {
    val title: String = title_c
    val content: String = content_c
    val id: Option[Long] = id_c

    def isEqual(other: ArticleEntity): Boolean = {
        (id, other.id) match {
            case (Some(id1), Some(id2)) => id1 == id2
            case (Some(id), None) => false
            case (None, Some(id)) => false
            case (None, None) => throw new Exception("Neither article has an id yet.")
        }
    }

    def toJson(): JsObject = {
        id match {
            case Some(n) => Json.obj("title" -> title, "content" -> content, "id" -> n)
            case None => Json.obj("title" -> title, "content" -> content)
        }
    }
}
