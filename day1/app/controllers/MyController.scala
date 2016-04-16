package controllers

import com.google.inject.Inject
import controllers.stack.ContextElement
import play.api.mvc.Controller
import services.MyService

class MyController @Inject() (service: MyService)
  extends Controller
  with ContextElement
{
  def index = StackAction { implicit req =>
    Ok(service.find("Alice"))
  }
}
