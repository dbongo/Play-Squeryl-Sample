package controllers

import play.api._
import play.api.mvc._
import model.CoreSchema
import model.User
import org.squeryl.PrimitiveTypeMode._
import play.api.libs.json.Json
import play.api.data._
import play.api.data.Forms._
import views.html.defaultpages.badRequest

object Application extends Controller {

  val insertForm = Form(
   tuple(
     "name" -> nonEmptyText,
     "email" -> nonEmptyText,
     "comment" -> text
   )
  )
  
  def javascriptRoutes = Action { implicit request =>
    import routes.javascript._
    Ok(
      Routes.javascriptRouter("jsRoutes")(
          routes.javascript.Application.insert,
          routes.javascript.Application.list
    )).as("text/javascript")
  }
  
  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }
  
  def insert = Action { implicit request =>
    insertForm.bindFromRequest.fold(
      formWithErros => BadRequest(Json.obj("error" -> "Bad Request!")),
      input => {
        var newUser: User = null
        inTransaction {
          newUser = CoreSchema.users.insert(new User(input._1, input._2, input._3))
        }
        Ok(toJSON(newUser))
      }
    )
  }
  
  def list = Action {
    inTransaction {
      val users = for (user <- CoreSchema.users.seq) yield toJSON(user)
      Ok(Json.obj(
       "users" -> users
      ))
    }
  }

  def toJSON(user: User) = {
    Json.obj(
      "id" -> user.id,
      "name" -> user.name,
      "created_at" -> user.createdAt,
      "email" -> user.email,
      "comment" -> user.comment
    )
  }
  
}