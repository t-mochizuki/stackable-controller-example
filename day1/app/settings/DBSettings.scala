package settings

import scalikejdbc._, config._

trait DBSettings {
  DBSettings.initialize()
}

object DBSettings {

  private var isInitialized = false

  def initialize(): Unit = this.synchronized {
    if (isInitialized) return
    DBs.setupAll()
    GlobalSettings.loggingSQLErrors = false
    GlobalSettings.sqlFormatter = SQLFormatterSettings("utils.HibernateSQLFormatter")
    DBInitializer.run()
    isInitialized = true
  }

}

object DBInitializer {

  def run(): Unit = {
    DB autoCommit { implicit s =>
      sql"""
drop table members if exists;
create table members (
  id serial not null primary key,
  name varchar(64),
  created_at timestamp not null
);
""".execute.apply()

      Seq("Alice", "Bob", "Chris") foreach { name =>
        sql"insert into members (name, created_at) values (${name}, current_timestamp)".update.apply()
      }
    }
  }

}
