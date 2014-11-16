package net.stsmedia.akka.http

import akka.actor.ActorSystem

/**
 * Created by sschmidt on 14/11/14.
 */
object Akka {
  implicit val actorSystem = ActorSystem("actorsystem")
}
