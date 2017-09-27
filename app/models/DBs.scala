package models

import play.api.data.Forms._
import play.api.data._


case class Person(
                      personID: Int,
                      firstName: String,
                      lastName: String,
                      userName: String,
                      password: String,
                      addressLine1: String,
                      postcode: String,
                      phoneNumber: String)

case class Cinema(
                   cinemaID: Int,
                   addressLine1: String,
                   postcode: String)

case class Movie(
                   movieID: Int,
                   movieName: String)

case class Screens(
                  screenID: Int,
                  seatsAvailable: Int,
                  seatsUnAvailable: Int,
                  screenType: String,
                  cinemaID: Int)

case class Showings(
                    showingID: Int,
                    startTime: Int,
                    endTime: Int,
                    date: Int,
                    movieID: Int,
                    screenID: Int)

case class Ticket(
                     ticketID: Int,
                     ticketType: String,
                     price: Int)

case class CreditCard(
                   cardID: Int,
                   cardHoldersNme: String,
                   expiryDate: Int,
                   securityCode: Int,
                   personID: Int)







object Formats {

  import play.api.libs.json.Json

  // Generates Writes and Reads for Feed and User thanks to Json Macros


  val createPersonForm = Form(
    mapping(
      "ID" -> number,
      "First Name" -> nonEmptyText,
      "Last Name" -> nonEmptyText,
      "User Name" -> nonEmptyText,
      "password" -> nonEmptyText,
      "addressLine1" -> nonEmptyText,
      "postcode" -> nonEmptyText,
      "phonenumber" -> nonEmptyText
    )(Person.apply)(Person.unapply)
  )

  val createCinemaForm = Form(
    mapping(
      "ID" -> number,
      "addressLine1" -> nonEmptyText,
      "postcode" -> nonEmptyText
    )(Cinema.apply)(Cinema.unapply)
  )

  val createMovieForm = Form(
    mapping(
      "ID" -> number,
      "Movie Name" -> nonEmptyText
    )(Movie.apply)(Movie.unapply)
  )

  val createScreensForm = Form(
    mapping(
      "ID" -> number,
      "Available Seats" -> number,
      "Un-Available Seats" -> number,
      "Screen Type" -> nonEmptyText,
      "CinemaID" -> number
    )(Screens.apply)(Screens.unapply)
  )

  val createShowingsForm = Form(
    mapping(
      "ID" -> number,
      "start Time" -> number,
      "end Time" -> number,
      "date" -> number,
      "MovieID" -> number,
      "screenID" -> number
    )(Showings.apply)(Showings.unapply)
  )

  val createTicketForm = Form(
    mapping(
      "ID" -> number,
      "ticket type" -> nonEmptyText,
      "price" -> number(min = 0, max = 100)
    )(Ticket.apply)(Ticket.unapply)
  )

  val createCreditCardForm = Form(
    mapping(
      "ID" -> number,
      "Card Holders Name" -> nonEmptyText,
      "expiry Date" -> number,
      "security Code" -> number,
      "personID" -> number
    )(CreditCard.apply)(CreditCard.unapply)
  )



  implicit val personFormat = Json.format[Person]
  implicit val cinemaFormat = Json.format[Cinema]
  implicit val movieFormat = Json.format[Movie]
  implicit val screensFormat = Json.format[Screens]
  implicit val showingsFormat = Json.format[Showings]
  implicit val ticketFormat = Json.format[Ticket]
  implicit val creditCardFormat = Json.format[CreditCard]

}



