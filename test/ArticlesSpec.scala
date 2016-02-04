import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._

import play.api.test._
import play.api.test.Helpers._

/**
 * Add your spec here.
 * You can mock out a whole application including requests, plugins etc.
 * For more information, consult the wiki.
 */
@RunWith(classOf[JUnitRunner])
class ArticlesSpec extends Specification {

  "Articles" should {

    "require authentication" in new WithApplication{
      route(FakeRequest(GET, "/articles")) must beSome.which (status(_) == UNAUTHORIZED)
      route(FakeRequest(GET, "/articles").withHeaders("Authorization" -> play.Play.application.configuration.getString("authentication.key"))) must beSome.which (status(_) == OK)
    }
  }
}
