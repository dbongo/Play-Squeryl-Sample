package model

import java.sql.Timestamp

import org.squeryl.KeyedEntity
import org.squeryl.PrimitiveTypeMode.long2ScalarLong
import org.squeryl.Schema
import org.squeryl.annotations.Column

class BaseEntity extends KeyedEntity[Long] {
  val id: Long = 0
}

trait CreationTimeMonitoring {
  @Column("created_at")
  val createdAt: Timestamp = new Timestamp(System.currentTimeMillis)
}

class User(var name: String,
           var email: String,
           var comment: String) extends BaseEntity with CreationTimeMonitoring {
  def this() = this("", "", "")
}

object CoreSchema extends Schema {
  val users = table[User]("user")
  
  on(users)(entity => declare(
    entity.id is(autoIncremented)
  ))
}
