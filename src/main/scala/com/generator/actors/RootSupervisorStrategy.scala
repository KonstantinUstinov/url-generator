package com.generator.actors

import akka.actor.SupervisorStrategy.{Restart, Stop}
import akka.actor.{ActorInitializationException, ActorKilledException, ActorLogging, DeathPactException, OneForOneStrategy, SupervisorStrategy, SupervisorStrategyConfigurator}

class RootSupervisorStrategy extends SupervisorStrategyConfigurator {
  override def create(): SupervisorStrategy = {

    OneForOneStrategy()({
      case _: ActorInitializationException => Stop
      case _: ActorKilledException => Stop
      case _: DeathPactException => Stop
      case e: Exception =>
        Console.print("Root level actor crashed, will be restarted", e)
        Restart
    })
  }

}
