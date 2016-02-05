import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._

import play.api.test._
import play.api.test.Helpers._

import domain.articles.factory.ArticlesFactory

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

    def create = new WithApplication {
        val articlesFactory = ArticlesFactory()
    }

}