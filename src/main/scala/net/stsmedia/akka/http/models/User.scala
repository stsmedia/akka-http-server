package net.stsmedia.akka.http.models

import reactivemongo.bson.{BSONDocument, BSONDocumentReader, BSONDocumentWriter, BSONObjectID}
import spray.json._

/**
 * Created by sschmidt on 14/11/14.
 */
case class User(id: BSONObjectID, name: String)

object User extends DefaultJsonProtocol {
  implicit object UserReader extends BSONDocumentReader[User] {
    def read(doc: BSONDocument): User = {
      val id = doc.getAs[BSONObjectID]("_id").get
      val name = doc.getAs[String]("name").get
      User(id, name)
    }
  }

  implicit object UserWriter extends BSONDocumentWriter[User] {
    override def write(u: User): BSONDocument = BSONDocument {
      "name" -> u.name
    }
  }

  implicit object UserJsonFormat extends RootJsonFormat[User] {
    def write(u: User) = JsObject("name" -> JsString(u.name))

    def read(value: JsValue) = value match {
      case JsArray(Vector(JsString(name))) =>
        new User(null, name)
      case _ => deserializationError("User expected")
    }
  }
}
