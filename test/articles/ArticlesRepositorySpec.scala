import org.specs2.concurrent.ExecutionEnv
import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._

import play.api.test._
import play.api.test.Helpers._
import play.api.libs.json._

import daos.ArticlesDAO
import domain.entities.ArticleEntity
import domain.factories.ArticlesFactory

/**
 * Add your spec here.
 * You can mock out a whole application including requests, plugins etc.
 * For more information, consult the wiki.
 */
@RunWith(classOf[JUnitRunner])
class ArticlesRepositorySpec(implicit ee: ExecutionEnv) extends Specification { override def is = s2"""
    The Articles repository should
        add a new article        $add
        """

    val dao = new ArticlesDAO()
    val factory = new ArticlesFactory()

    def add = new WithApplication {
        val result = dao.save(new ArticleEntity("title", "content", None))
        result must beSome.await
    }
}
