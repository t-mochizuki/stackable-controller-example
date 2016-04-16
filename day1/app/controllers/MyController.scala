package controllers

import com.google.inject.Inject
import controllers.stack.DBSessionElement
import play.api.mvc.Controller
import services.MyService

class MyController @Inject() (service: MyService) extends Controller with DBSessionElement {
  def index = StackAction { implicit req =>
    Ok(service.find("Alice"))
  }
}
