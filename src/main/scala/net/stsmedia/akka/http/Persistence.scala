package net.stsmedia.akka.http

/**
 * Created by sschmidt on 15/11/14.
 */
trait Persistence {

  implicit val executionContext = Akka.actorSystem
  import executionContext.dispatcher

  def db = {
    import reactivemongo.api._

    // gets an instance of the driver
    // (creates an actor system)
    val driver = new MongoDriver
    val connection = driver.connection(List("mongodb"))

    // Gets a reference to the database "plugin"
    connection("stsmedia")
  }
}
