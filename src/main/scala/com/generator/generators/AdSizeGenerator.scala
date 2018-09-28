package com.generator.generators

import com.generator.generators.IpGenerator.getRandomInt


object AdSizeGenerator {
  val list = Map(0 -> "4938160", 1 -> "4938163", 2 -> "4938161", 3 -> "4938162", 4 ->"4938159")

  def getRandomAdSize : String = {
    list(getRandomInt(0, list.size - 1))
  }

}
