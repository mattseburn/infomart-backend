package controllers

import play.api._
import play.api.mvc._
import play.api.libs.json._

import actions.Authenticated

class Articles extends Controller {
    def index = Authenticated {
        Ok("list articles")
    }

    def create = Authenticated {
        // use article factory to create new article
        // add it to article repository
        // return it
        Ok(Json.obj("content" -> "The Content", "title" -> "The Title"))
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
