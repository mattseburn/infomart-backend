package actions

import play.api.mvc._
import play.api.mvc.Results._
import scala.concurrent.Future

object Authenticated extends ActionBuilder[Request] {
    def invokeBlock[A](request: Request[A], block: (Request[A]) => Future[Result]) = {
        val key = play.Play.application.configuration.getString("authentication.key")
        request.headers.get("Authorization").collect {
            case key => block(request)
        } getOrElse {
            Future.successful(Unauthorized("Not authenticated"))
        }
    }
}
