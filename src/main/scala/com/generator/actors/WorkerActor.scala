package com.generator.actors

import akka.actor.{Actor, ActorLogging}
import com.generator.generators._
import akka.pattern.pipe
import scalaj.http.{Http, HttpResponse}
import java.net.URLEncoder
import java.util.concurrent.Executors

import scala.concurrent.{ExecutionContext, Future}



class WorkerActor extends Actor with ActorLogging {

  implicit val ec = ExecutionContext.fromExecutor(Executors.newFixedThreadPool(10))

  def receive = {
    case Message =>

      val url = s"http://adserver.adtech.advertising.com/addyn"
      val params = s"|3.0|11527.1|${AdSizeGenerator.getRandomAdSize}|0|154|ADTECH;loc=100;target=_blank;misc=${System.currentTimeMillis()};rdclick="

      val ip = ipListGenerator.getRandomIp
      val agent = UserAgentsGenerator.getRandomAgent
      val referer = DomainGenerator.getRandomDomain
      log.debug(s"url=${url + params} X-Forwarded-For : $ip User-Agent : $agent Referer : $referer")

      val headers = Map("User-Agent" -> agent, "X-Forwarded-For" -> ip, "Referer" -> referer)
      val request = Http(url + params).method("GET").headers(headers)

      //URLEncoder.encode(params, "utf-8")

      Future{request.asString}.pipeTo(self)

    case result : HttpResponse[String]  =>
      log.debug(s"${result.code} body= ${result.body} headers= ${result.headers}")

    case _ =>
      log.debug("Not expected Msg")
  }

}
