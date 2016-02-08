package controllers

import play.api._
import play.api.mvc._
import play.api.mvc.BodyParsers.parse
import play.api.libs.json._

import actions.Authenticated

import domain.factories.ArticlesFactory
import domain.repositories.ArticlesRepository

import scala.concurrent.ExecutionContext.Implicits.global

class Articles extends Controller {
    val factory = new ArticlesFactory()
    val repository = new ArticlesRepository()

    def index = Authenticated {
        Ok("list articles")
    }

    def create = Authenticated(parse.json) { request =>
        // use article factory to create new article

        /*
        factory.build(request.body.as[JsObject]) match {
            case Some(article) => {
                // add it to article repository
                repository.add(article) onComplete {
                    case r => r match {
                        case Some(a) => Ok(Json.obj("title" -> a.title, "content" -> a.content, "id" -> a.id))
                        case None => println(None)
                    }
                }
                // return it
                //Ok(article)
            }
            case None => Status(500)("Article creation failed.")
        }
        */
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
