package com.generator.generators

import org.scalatest.{FlatSpec, Matchers}

class ipListGeneratorSpec extends FlatSpec with Matchers {

  "ipListGenerator" should "fill from file" in {
    ipListGenerator.fillGenerator()
    succeed
  }

  "IpGenerator" should "return Random IP" in {
    (0 to 100).map(_ => ipListGenerator.getRandomIp).foreach(println(_))

    succeed
  }

}
