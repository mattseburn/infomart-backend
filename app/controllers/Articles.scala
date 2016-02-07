package controllers

import play.api._
import play.api.mvc._
import play.api.mvc.BodyParsers.parse
import play.api.libs.json._

import actions.Authenticated

import domain.factories.ArticlesFactory

class Articles extends Controller {
    val factory = new ArticlesFactory()

    def index = Authenticated {
        Ok("list articles")
    }

    def create = Authenticated(parse.json) { request =>
        // use article factory to create new article

        factory.build(request.body.as[JsObject]) match {
            case Some(article) => {
                // add it to article repository
                // return it
                //Ok(article)
            }
            case None => Status(500)("Article creation failed.")
        }

        Ok(Json.obj())
    }

    /*
    def get(id: Long) = Authenticated {
        Ok("get article " + id)
    }

    def update(id: Long) = Authenticated {
        Ok("update article " + id)
    }

    def delete(id: Long) = Authenticated {
        Ok("delete article " + id)
    }
    */
}
