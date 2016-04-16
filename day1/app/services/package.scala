import models.shared.MyContext
import scalikejdbc.DBSession

package object services {
  implicit def contextToSession(implicit context: MyContext): DBSession = context.session
}
