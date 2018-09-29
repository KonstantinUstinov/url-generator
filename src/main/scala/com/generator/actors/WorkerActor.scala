package com.generator.actors

import akka.actor.{Actor, ActorLogging}
import com.generator.generators.{AdSizeGenerator, DomainGenerator, IpGenerator, UserAgentsGenerator}
import akka.pattern.pipe

import scalaj.http.{Http, HttpResponse}
import java.net.URLEncoder
import java.util.concurrent.Executors

import scala.concurrent.{ExecutionContext, Future}



class WorkerActor extends Actor with ActorLogging {

  implicit val ec = ExecutionContext.fromExecutor(Executors.newFixedThreadPool(10))

  def receive = {
    case Message =>

      val url = s"http://${DomainGenerator.getRandomDomain}/addyn"
      val params = s"|3.0|11527.1|${AdSizeGenerator.getRandomAdSize}|0|154|ADTECH;loc=100;target=_blank;misc=[TIMESTAMP];rdclick="

      val ip = IpGenerator.getRandomIp
      val agent = UserAgentsGenerator.getRandomAgent
      log.debug(s"url=${url + URLEncoder.encode(params, "utf-8")} X-Forwarded-For : $ip User-Agent : $agent")

      val headers = Map("User-Agent" -> agent, "X-Forwarded-For" -> ip)
      val request = Http(url + URLEncoder.encode(params, "utf-8")).headers(headers)

      Future{request.asString}.pipeTo(self)

    case result : HttpResponse[String]  => log.debug(s"${result.code} body= ${result.body}")

    case _ =>
      log.debug("Not expected Msg")
  }

}
