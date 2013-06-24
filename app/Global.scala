import org.squeryl.Session
import org.squeryl.SessionFactory
import org.squeryl.adapters.H2Adapter

import play.api.Application
import play.api.GlobalSettings
import play.api.db.DB

object Global extends GlobalSettings {
  override def onStart(app: Application) {
    SessionFactory.concreteFactory = Some( () => connection )
    def connection() = {
      Session.create(DB.getConnection()(app), new H2Adapter)
    }
  }
}
