package controllers

import javax.inject.Inject
import play.api.i18n.{I18nSupport, MessagesApi}
import play.api.mvc._
import play.modules.reactivemongo.{MongoController, ReactiveMongoApi, ReactiveMongoComponents}

class Application @Inject()(val messagesApi: MessagesApi, val reactiveMongoApi: ReactiveMongoApi)
    extends Controller with I18nSupport with MongoController with ReactiveMongoComponents {


  def index = Action {
    Ok(views.html.index())
  }

  def e404 = Action {
    Ok(views.html.e404())
  }

  def classifications = Action {
    Ok(views.html.classifications())
  }

}