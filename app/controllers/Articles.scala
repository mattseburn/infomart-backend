package controllers

import play.api._
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.mvc._
import play.api.mvc.BodyParsers.parse
import play.api.libs.json._

import actions.Authenticated

import domain.entities.ArticleEntity
import domain.factories.ArticlesFactory
import domain.repositories.ArticlesRepository

import scala.concurrent._

class Articles extends Controller {
    val factory = new ArticlesFactory()
    val repository = new ArticlesRepository()

    private def toJson(articles: Option[List[ArticleEntity]]): JsArray = {
        articles match {
            case Some(a) => {
                var json = Json.arr()
                for (article <- a) {
                    json = json :+ (article.toJson())
                }
                json
            }
            case None => {
                Json.arr()
            }
        }
    }

    def index = Authenticated.async { request =>
        repository.retrieve().map {o => Ok(toJson(o))}
    }

    def create = Authenticated.async(parse.json) { request =>
        // use article factory to create new article

        val article = factory.build(request.body.as[JsObject])
        repository.add(article).map { id =>
            Ok(Json.obj("title" -> article.title, "content" -> article.content, "id" -> id))
        }
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
