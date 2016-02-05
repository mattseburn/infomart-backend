import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._

import play.api.test._
import play.api.test.Helpers._
import play.api.mvc._
import scala.concurrent.Future

/**
 * Add your spec here.
 * You can mock out a whole application including requests, plugins etc.
 * For more information, consult the wiki.
 */
@RunWith(classOf[JUnitRunner])
class ArticlesControllerSpec extends Specification { override def is = s2"""
    The Articles controller should
        require authentication on all available routes      $e1
                                    """

    def e1 = new WithApplication {
        val requests = Array((GET, "/articles"))

        for (request <- requests) {
            unauthenticatedRequest(request) must beSome.which (status(_) == UNAUTHORIZED)
            authenticatedRequest(request) must beSome.which (status(_) == OK)
        }
    }

    def unauthenticatedRequest(request : (String, String)) : Option[Future[play.api.mvc.Result]] = {
        return route(FakeRequest(request._1, request._2))
    }

    def authenticatedRequest(request : (String, String)) : Option[Future[play.api.mvc.Result]] = {
        val headers = "Authorization" -> play.Play.application.configuration.getString("authentication.key")
        return route(FakeRequest(request._1, request._2).withHeaders(headers))
    }
}
