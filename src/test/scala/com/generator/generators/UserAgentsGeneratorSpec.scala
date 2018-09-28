package com.generator.generators

import org.scalatest.{FlatSpec, Matchers}

class UserAgentsGeneratorSpec extends FlatSpec with Matchers {

  "UserAgentsGenerator" should "fill from file" in {
    UserAgentsGenerator.fillGenerator()
    succeed
  }

  "UserAgentsGenerator" should "return Random Agent" in {
    (0 to 100).map(_ => UserAgentsGenerator.getRandomAgent).foreach(println(_))

    succeed
  }
}