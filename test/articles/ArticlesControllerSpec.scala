import org.specs2.mutable._
import org.specs2.runner._
import org.specs2.specification.core.Fragments
import org.junit.runner._

import play.api.test._
import play.api.test.Helpers._
import play.api.mvc._
import play.api.libs.json._
import scala.concurrent.Future

trait BeforeAllAfterAll extends Specification {
    override def map(fragments: => Fragments) =
        step(before) ^ fragments ^ step(after)
    protected def before()
    protected def after()
}

/**
 * Add your spec here.
 * You can mock out a whole application including requests, plugins etc.
 * For more information, consult the wiki.
 */
@RunWith(classOf[JUnitRunner])
class ArticlesControllerSpec extends Specification with BeforeAllAfterAll { override def is = s2"""
    The Articles controller should
        require authentication on all available routes  $authentication
        create articles                                 $create
        list articles                                   $list
        """


    def before() {
        println("setup")
    }

    def after() {
        println("teardown")
    }

    def authentication = new WithApplication {
        val requests = Array(
                (GET, "/articles"),
                (POST, "/articles")
            )

        for (request <- requests) {
            unauthenticatedRequest(request._1, request._2) must beSome.which (status(_) == UNAUTHORIZED)
        }
    }

    def create = new WithApplication {
        val result = authenticatedRequest(POST, "/articles", Json.obj("title" -> "The Title", "content" -> "The Content"))
        result must beSome.which (status(_) == OK)
        result.foreach(r => {
            val json = Json.parse(contentAsString(r))
            json.as[JsObject].keys must contain(eachOf("title", "content", "id"))
            (json \ "title").as[String] must equalTo("The Title")
            (json \ "content").as[String] must equalTo("The Content")
        })
    }

    def list = new WithApplication {
        val result = authenticatedRequest(GET, "/articles", Json.obj())
        result must beSome.which (status(_) == OK)
        result.foreach(r => {
        })
    }

    private def unauthenticatedRequest(method: String, uri: String): Option[Future[play.api.mvc.Result]] = {
        return route(FakeRequest(method, uri)
                .withJsonBody(Json.obj()))
    }

    private def authenticatedRequest(method: String, uri: String, body: JsValue = Json.obj()) : Option[Future[play.api.mvc.Result]] = {
        return route(FakeRequest(method, uri)
                .withHeaders("Authorization" -> play.Play.application.configuration.getString("authentication.key"))
                .withJsonBody(body))
    }
}
