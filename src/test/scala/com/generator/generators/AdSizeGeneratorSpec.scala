package com.generator.generators

import org.scalatest.{FlatSpec, Matchers}


class AdSizeGeneratorSpec extends FlatSpec with Matchers {

  "AdSizeGenerator" should "return Size" in {
    (0 to 100).map(_ => AdSizeGenerator.getRandomAdSize).foreach(println(_))

    succeed
  }

}