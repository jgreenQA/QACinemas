package controllers

import javax.inject.Inject

import models.{Movie, Person}
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

  def collectionPersons: Future[JSONCollection] = database.map(
    _.collection[JSONCollection]("persons"))

  def collectionCinemas: Future[JSONCollection] = database.map(
    _.collection[JSONCollection]("cinemas"))

  def collectionMovies: Future[JSONCollection] = database.map(
    _.collection[JSONCollection]("Movies"))

  def collectionScreens: Future[JSONCollection] = database.map(
    _.collection[JSONCollection]("Screens"))

  def collectionShowings: Future[JSONCollection] = database.map(
    _.collection[JSONCollection]("Showings"))

  def collectionTickets: Future[JSONCollection] = database.map(
    _.collection[JSONCollection]("Tickets"))

  def collection: Future[JSONCollection] = database.map(
    _.collection[JSONCollection]("CreditCards"))

  def createPerson = Action.async { implicit request =>
    val record = Person(3, "dave", "smith", "bobosmith", "stuf", "11 street", "78d lkd", "0238902348204820")

    // insert the DB
    val futureResult = collectionPersons.flatMap(_.insert(record))

    // when the insert is performed, send a OK 200 result
    futureResult.map(_ => Ok("Successfully added user"))

  }


  def findByName(lastName: String) = Action.async { implicit request =>
    // let's do our query
    val cursor: Future[Cursor[Person]] = collection.map {
      // find all people with name `name`
      _.find(Json.obj("lastName" -> lastName)).
        // sort them by creation date
        sort(Json.obj("created" -> -1)).
        // perform the query and get a cursor of JsObject
        cursor[Person]
    }

    // gather all the JsObjects in a list
    val futureDBsList: Future[List[Person]] = cursor.flatMap(_.collect[List]())

    // everything's ok! Let's reply with the array
    futureDBsList.map { persons =>
      Ok(persons.toString)
    }
  }


  def createMovie = Action.async { implicit request =>
    //val record = Movie("tt3967856", "Okja")
        //val record = Movie("tt1790809" ,"Pirates of the Caribbean: Dead Men Tell No Tales")
      // val record = Movie("tt3758172" ,"Jungle")
       // val record = Movie("tt4731136" ,"A Cure for Wellness")
    //  val record = Movie("tt3731562" ,"Kong: Skull Island")
    // val record = Movie("tt3450958" ,"War for the Planet of the Apes")

       // val record = Movie("tt3896198" ,"Guardians of the Galaxy Vol. 2" )
       // val record = Movie("tt3501632" ,"Thor: Ragnarok" )
      // val record = Movie("tt4425200" ,"John Wick: Chapter 2" )
        val record = Movie("tt3315342" ,"Logan" )

    // insert the DB
    val futureResult = collectionMovies.flatMap(_.insert(record))

    // when the insert is performed, send a OK 200 result
    futureResult.map(_ => Ok("Successfully added movie"))

  }


  def findMovieByName(movieName: String) = Action.async { implicit request =>
    // let's do our query
    val cursor: Future[Cursor[Movie]] = collectionMovies.map {
      // find all people with name `name`
      _.find(Json.obj("lastName" -> movieName)).
        // sort them by creation date
        sort(Json.obj("created" -> -1)).
        // perform the query and get a cursor of JsObject
        cursor[Movie]
    }

    // gather all the JsObjects in a list
    val futureDBsList: Future[List[Movie]] = cursor.flatMap(_.collect[List]())

    // everything's ok! Let's reply with the array
    futureDBsList.map { movies =>
      Ok(createMovieIDFromResponse(Movie.toString()))
    }

  }


  def createMovieIDFromResponse(film: String): String = {
    val filmArray = film.substring(0, 8)
    filmArray
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