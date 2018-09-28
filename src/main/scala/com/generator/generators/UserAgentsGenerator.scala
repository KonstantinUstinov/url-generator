package com.generator.generators

import java.util.concurrent.ConcurrentHashMap
import scala.collection.JavaConverters._
import com.generator.generators.IpGenerator.getRandomInt


object UserAgentsGenerator {

  val SourceFileName = "useragents.txt"
  val agentRange: ConcurrentHashMap[Int, String] = new ConcurrentHashMap

  def fillGenerator(): Unit = {
    import scala.io.Source
    val content = Source.fromResource(SourceFileName).getLines.map(_.trim).filterNot(_.isEmpty).toList.zipWithIndex

    agentRange.putAll(content.toMap.map(d => (d._2, d._1)).asJava)
  }

  def getRandomAgent : String = {
    agentRange.get(getRandomInt(0, agentRange.size() - 1))
  }

}
