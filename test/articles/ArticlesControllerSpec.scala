import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._

import play.api.test._
import play.api.test.Helpers._
import play.api.mvc._
import play.api.libs.json._
import scala.concurrent.Future

/**
 * Add your spec here.
 * You can mock out a whole application including requests, plugins etc.
 * For more information, consult the wiki.
 */
@RunWith(classOf[JUnitRunner])
class ArticlesControllerSpec extends Specification { override def is = s2"""
    The Articles controller should
        require authentication on all available routes  $authentication
        create articles                                 $creation
        """

    def authentication = new WithApplication {
        val requests = Array(
                (GET, "/articles"),
                (POST, "/articles")
            )

        for (request <- requests) {
            status(unauthenticatedRequest(request._1, request._2)) must equalTo (UNAUTHORIZED)
        }
    }

    def creation = new WithApplication {
        val result = authenticatedRequest(POST, "/articles", Json.obj("title" -> "The Title", "content" -> "The Content"))
        val json = Json.parse(contentAsString(result))

        status(result) must equalTo (OK)
        (json \ "title").as[String] must equalTo("The Title")
        (json \ "content").as[String] must equalTo("The Content")
    }

    private def unauthenticatedRequest(method : String, uri : String) : Future[play.api.mvc.Result] = {
        val Some(result) = route(FakeRequest(method, uri))
        return result
    }

    private def authenticatedRequest(method : String, uri : String, body: JsValue = Json.obj()) : Future[play.api.mvc.Result] = {
        val Some(result) = route(FakeRequest(method, uri)
                .withHeaders("Authorization" -> play.Play.application.configuration.getString("authentication.key"))
                .withJsonBody(body))
        return result
    }
}
