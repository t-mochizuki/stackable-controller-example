package controllers.stack

import play.api.mvc.{Controller, Result}
import scalikejdbc._
import jp.t2v.lab.play2.stackc.{RequestAttributeKey, RequestWithAttributes, StackableController}
import settings.DBSettings

import scala.concurrent.Future

trait DBSessionElement extends StackableController {
  self: Controller =>

  case object DBSessionKey extends RequestAttributeKey[DBSession]

  override def proceed[A](req: RequestWithAttributes[A])
                         (f: RequestWithAttributes[A] => Future[Result]): Future[Result] = {
    DBSettings.initialize()
    DB.localTx { session =>
      super.proceed(req.set(DBSessionKey, session))(f)
    }
  }

  implicit def dbSession[A](implicit req: RequestWithAttributes[A]): DBSession = req.get(DBSessionKey).get
}
