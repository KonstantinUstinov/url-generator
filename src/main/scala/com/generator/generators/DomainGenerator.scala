package com.generator.generators

import java.util.concurrent.ConcurrentHashMap
import com.generator.generators.IpGenerator.getRandomInt
import scala.collection.JavaConverters._

object DomainGenerator {

  val SourceFileName = "domain.txt"
  val domainRange: ConcurrentHashMap[Int, String] = new ConcurrentHashMap

  def fillGenerator(): Unit = {
    import scala.io.Source
    val content = Source.fromResource(SourceFileName).getLines.map(_.trim).filterNot(_.isEmpty).toList.zipWithIndex

    domainRange.putAll(content.toMap.map(d => (d._2, d._1)).asJava)
  }

  def getRandomDomain : String = {
    domainRange.get(getRandomInt(0, domainRange.size() - 1))
  }

}
