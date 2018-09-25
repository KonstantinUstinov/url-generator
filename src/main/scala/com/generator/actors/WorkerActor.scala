package com.generator.actors

import akka.actor.{Actor, ActorLogging}

class WorkerActor extends Actor with ActorLogging {

  def receive = {
    case Message =>
      log.debug("Generate url")
    case _ =>
      log.debug("Not expected Msg")
  }

}
