package controllers.stack

import jp.t2v.lab.play2.stackc.{RequestAttributeKey, RequestWithAttributes, StackableController}
import models.shared.MyContext
import play.api.mvc.{Controller, Result}

import scala.concurrent.Future

trait ContextElement
  extends StackableController
  with DBSessionElement
{
  self: Controller =>

  case object ContextKey extends RequestAttributeKey[MyContext]

  override def proceed[A](req: RequestWithAttributes[A])
                         (f: RequestWithAttributes[A] => Future[Result]): Future[Result] = {
    super.proceed(req) { implicit r =>
      f(r.set(ContextKey, MyContext(dbSession)))
    }
  }

  implicit def context[A](implicit req: RequestWithAttributes[A]): MyContext = req.get(ContextKey).get
}
