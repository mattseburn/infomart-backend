import org.specs2.concurrent.ExecutionEnv
import org.specs2.mutable._
import org.specs2.runner._
import org.specs2.execute.Failure
import org.junit.runner._

import play.api.test._
import play.api.test.Helpers._
import play.api.libs.json._

import daos.ArticlesDAO
import domain.entities.ArticleEntity
import domain.factories.ArticlesFactory
import domain.repositories.ArticlesRepository

import scala.concurrent.Await
import scala.concurrent.Future
import scala.concurrent.duration.Duration

/**
 * Add your spec here.
 * You can mock out a whole application including requests, plugins etc.
 * For more information, consult the wiki.
 */
@RunWith(classOf[JUnitRunner])
class ArticlesRepositorySpec(implicit ee: ExecutionEnv) extends Specification { override def is = s2"""
    The Articles repository should
        add a new article        $add
        retrieve articles        $retrieve
        """

    val repository = new ArticlesRepository()

    def add = new WithApplication {
        // create new article entity
        // add it to repository
        // verify that id is returned

        repository.add(new ArticleEntity("title", "content", None)) must beSome[Long].await
    }

    def retrieve = new WithApplication {
        repository.retrieve() must beSome[List[ArticleEntity]].await
    }
}
