package com.generator.generators

import org.scalatest.{FlatSpec, Matchers}


class DomainGeneratorSpec extends FlatSpec with Matchers {

  "DomainGenerator" should "fill from file" in {
    DomainGenerator.fillGenerator()
    succeed
  }

  "DomainGenerator" should "return Random Domain" in {
    (0 to 100).map(_ => DomainGenerator.getRandomDomain).foreach(println(_))

    succeed
  }
}