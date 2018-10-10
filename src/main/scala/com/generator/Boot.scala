package com.generator

import java.util.concurrent.TimeUnit

import akka.actor.{ActorSystem, Props}
import akka.event.Logging
import com.generator.actors.{Message, SchedulerActor}
import com.generator.generators.{DomainGenerator, IpGenerator, UserAgentsGenerator, ipListGenerator}
import com.generator.utils.ConfigProvider

import scala.concurrent.duration._

object  Boot extends App with ConfigProvider{

  implicit val system = ActorSystem()
  implicit val executor = system.dispatcher

  val logger = Logging(system, getClass)

  val actor = system.actorOf(Props(new SchedulerActor), name = "actor")

  DomainGenerator.fillGenerator()
  IpGenerator.fillGenerator()
  ipListGenerator.fillGenerator()
  UserAgentsGenerator.fillGenerator()

  system.scheduler.schedule(1 seconds, FiniteDuration(config.getDuration("scheduler.interval").toNanos, TimeUnit.NANOSECONDS), actor, Message())

}
