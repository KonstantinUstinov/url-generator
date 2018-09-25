package com.generator.actors

import akka.actor.{Actor, ActorLogging, Props}
import akka.routing.{ActorRefRoutee, BroadcastRoutingLogic, Router}
import com.generator.utils.ConfigProvider

case class Message()

class SchedulerActor extends Actor with ActorLogging with ConfigProvider {

  var router = {
    val routees = Vector.fill(config.getInt("workers.count")) {
      val r = context.actorOf(Props[WorkerActor])
      context watch r
      ActorRefRoutee(r)
    }
    Router(BroadcastRoutingLogic(), routees)
  }

  def receive = {
    case Message() =>
      log.debug("SchedulerActor get msg")
      router.route(Message, this.self)
    case _ =>
      log.debug("Not expected Msg")
  }

}
