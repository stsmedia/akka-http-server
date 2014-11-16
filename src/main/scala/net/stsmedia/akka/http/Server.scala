package net.stsmedia.akka.http

import akka.http.Http
import akka.http.server.ScalaRoutingDSL
import akka.http.server.ScalaRoutingDSL._
import akka.io.IO
import akka.pattern.ask
import akka.stream.FlowMaterializer
import akka.util.Timeout
import net.stsmedia.akka.http.models.User
import net.stsmedia.akka.http.support.SprayJsonSupport
import reactivemongo.api.collections.default.BSONCollection
import reactivemongo.bson._

import scala.concurrent.duration._

object Server extends App with Persistence {

  import executionContext.dispatcher
  implicit val materializer = FlowMaterializer()
  implicit val askTimeout: Timeout = 500.millis
  val bindingFuture = (IO(Http) ? Http.Bind(interface = "0.0.0.0", port = 8080)).mapTo[Http.ServerBinding]

  import User._

  implicit val marshaller = SprayJsonSupport.sprayJsonMarshaller[List[User]]

  handleConnections(bindingFuture) withRoute {
    path("users") {
      get {
        complete {
          db[BSONCollection]("users").find(BSONDocument()).cursor[User].collect[List](25)
        }
      } ~
        post {
          parameterMap { map =>
            db[BSONCollection]("users").insert(User(null, map("name")))
            complete {
              "thanks!"
            }
          }
        }
    }
  }
}
