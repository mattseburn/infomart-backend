import org.specs2.concurrent.ExecutionEnv
import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._

import play.api.test._
import play.api.test.Helpers._

import daos.ArticlesDAO
import domain.entities.ArticleEntity

import scala.concurrent._
import scala.util.{Success, Failure}

/**
* Add your spec here.
* You can mock out a whole application including requests, plugins etc.
* For more information, consult the wiki.
*/
@RunWith(classOf[JUnitRunner])
class ArticlesDAOSpec(implicit ee: ExecutionEnv) extends Specification { override def is = s2"""
    The Articles DAO should
        create a new article        $create
        """

    val articlesDAO = new ArticlesDAO()

    def create = new WithApplication {
        articlesDAO.save(new ArticleEntity("title", "content")) must beSome.await
    }
}
