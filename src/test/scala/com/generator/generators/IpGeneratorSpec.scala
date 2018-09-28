package com.generator.generators

import org.scalatest.{FlatSpec, Matchers}

class IpGeneratorSpec extends FlatSpec with Matchers {

  "IpGenerator" should "fill from file" in {
    IpGenerator.fillGenerator()
    succeed
  }

  "IpGenerator" should "return Random IP" in {
    (0 to 100).map(_ => IpGenerator.getRandomIp).foreach(println(_))

    succeed
  }
}
