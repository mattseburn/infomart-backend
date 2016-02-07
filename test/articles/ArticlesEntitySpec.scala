import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._

import play.api.test._
import play.api.test.Helpers._
import play.api.libs.json._

import domain.entities.ArticleEntity

/**
 * Add your spec here.
 * You can mock out a whole application including requests, plugins etc.
 * For more information, consult the wiki.
 */
@RunWith(classOf[JUnitRunner])
class ArticleEntitySpec extends Specification { override def is = s2"""
    The Articles entity should
        determine equality      $equality
        """

    def equality = new WithApplication {
        val article1 = new ArticleEntity("title", "content", Option(1))
        val article2 = new ArticleEntity("title", "content", Option(2))

        article1.isEqual(article2) must beFalse
        article1.isEqual(article1) must beTrue

        val article3 = new ArticleEntity("title", "content", None)
        val article4 = new ArticleEntity("title", "content", None)

        article3.isEqual(article4) must throwAn[Exception]
        article3.isEqual(article1) must beFalse
    }
}
