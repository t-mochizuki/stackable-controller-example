package services

import com.google.inject.ImplementedBy
import scalikejdbc._

@ImplementedBy(classOf[MyServiceImpl])
trait MyService {
  def find(name: String)(implicit session: DBSession): String
}

class MyServiceImpl extends MyService {
  def find(name: String)(implicit session: DBSession): String = sql"select created_at from members where name = $name".map(rs => rs.jodaDateTime("created_at")).single().apply().toString
}
