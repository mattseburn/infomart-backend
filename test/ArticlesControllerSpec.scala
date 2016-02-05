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
class ArticlesControllerSpec extends Specification { override def is = s2"""
    The Articles controller should
        require authentication on all available routes      $e1

                                    """

    lazy private val headers = "Authorization" -> play.Play.application.configuration.getString("authentication.key")

    def e1 = new WithApplication {
        val requests = Array((GET, "/articles"))

        for (request <- requests) {
            route(FakeRequest(request._1, request._2)) must beSome.which (status(_) == UNAUTHORIZED)
            route(FakeRequest(request._1, request._2).withHeaders(headers)) must beSome.which (status(_) == OK)
        }
    }
}
