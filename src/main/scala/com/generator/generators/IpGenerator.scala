package com.generator.generators

import java.util.concurrent.ConcurrentHashMap

import scala.collection.JavaConverters._
import com.generator.ipranges.IP

import scala.util.{Random, Try}

object IpGenerator {

  val SourceFileName = "dbip-country-lite.csv"
  val ipRange: ConcurrentHashMap[Int, (IP, IP)] = new ConcurrentHashMap

  private def converToRange(arr: Array[String]) : Boolean = {
    Try(arr(2) == "US", IP(arr(0)), IP(arr(1))).map(_._1).getOrElse(false)
  }

  def fillGenerator(): Unit = {
    import scala.io.Source
    val content = Source.fromResource(SourceFileName).getLines.map(_.split(",")).filter(converToRange(_)).toList.zipWithIndex

    ipRange.putAll(content.toMap.map(d => (d._2, (IP(d._1(0)), IP(d._1(1))))).asJava)
  }

  def getRandomIp : String = {
    val range = ipRange.get(getRandomInt(0, ipRange.size() - 1))
    randomIpBetween(range._1, range._2).toString
  }

  private def randomIpBetween(start: IP, end : IP) : IP = {
    new IP(Random.nextInt(end.value - start.value) + start.value)
  }

  def getRandomInt(start: Int, end: Int) = {
    val rnd = new scala.util.Random
    start + rnd.nextInt( (end - start) + 1 )
  }

}
