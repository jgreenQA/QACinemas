package controllers

import javax.inject.Inject

import models.{ Persons}
import play.api.i18n.{I18nSupport, MessagesApi}
import play.api.libs.json.Json
import play.api.mvc._
import play.modules.reactivemongo.{MongoController, ReactiveMongoApi, ReactiveMongoComponents}
import reactivemongo.api.Cursor
import reactivemongo.play.json.collection.JSONCollection
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import reactivemongo.play.json._
import models.Formats._
import play.modules.reactivemongo.json.collection._

class Application @Inject()(val messagesApi: MessagesApi, val reactiveMongoApi: ReactiveMongoApi)
    extends Controller with I18nSupport with MongoController with ReactiveMongoComponents {

  def collection: Future[JSONCollection] = database.map(
    _.collection[JSONCollection]("everyone"))

  def create = Action.async { implicit request =>
    val record = Persons(3 ,"dave" , "smith" , "bobosmith" , "stuf" , "11 street" , "78d lkd" , "0238902348204820" )

    // insert the DB
    val futureResult = collection.flatMap(_.insert(record))

    // when the insert is performed, send a OK 200 result
    futureResult.map(_ => Ok("Successfully added user"))

  }


    def findByName(lastName: String) = Action.async { implicit request =>
      // let's do our query
      val cursor: Future[Cursor[Persons]] = collection.map {
        // find all people with name `name`
        _.find(Json.obj("lastName" -> lastName)).
          // sort them by creation date
          sort(Json.obj("created" -> -1)).
          // perform the query and get a cursor of JsObject
          cursor[Persons]
      }

      // gather all the JsObjects in a list
      val futureDBsList: Future[List[Persons]] = cursor.flatMap(_.collect[List]())

      // everything's ok! Let's reply with the array
      futureDBsList.map { persons =>
        Ok(persons.toString)
      }
    }

































  def index = Action {
    Ok(views.html.index())
  }

  def e404 = Action {
    Ok(views.html.e404())
  }

  def classifications = Action {
    Ok(views.html.classifications())
  }

  def showing = Action {
    Ok(views.html.showing())
  }
}