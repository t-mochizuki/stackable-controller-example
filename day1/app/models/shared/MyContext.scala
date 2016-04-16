package models.shared

import scalikejdbc.DBSession

case class MyContext(session: DBSession)
