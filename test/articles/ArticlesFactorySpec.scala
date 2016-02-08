import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._

import play.api.test._
import play.api.test.Helpers._
import play.api.libs.json._

import domain.factories.ArticlesFactory

/**
 * Add your spec here.
 * You can mock out a whole application including requests, plugins etc.
 * For more information, consult the wiki.
 */
@RunWith(classOf[JUnitRunner])
class ArticlesFactorySpec extends Specification { override def is = s2"""
    The Articles factory should
        create a new article        $create
        """

    val factory = new ArticlesFactory()

    def create = new WithApplication {
        val article = factory.build(Json.obj("title" -> "title", "content" -> "content"))
        article.title must beEqualTo("title")
        article.content must beEqualTo("content")
    }
}
