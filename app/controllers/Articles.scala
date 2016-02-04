package controllers

import play.api._
import play.api.mvc._

import actions.Authenticated

class Articles extends Controller {
    def index = Authenticated {
        Ok("list articles")
    }

    /*
    def create = Authenticated {
        Ok("create article")
    }

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
