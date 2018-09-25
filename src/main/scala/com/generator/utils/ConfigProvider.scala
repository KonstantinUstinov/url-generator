package com.generator.utils

import com.typesafe.config.ConfigFactory

trait ConfigProvider {
  lazy val config = ConfigFactory.load()
}
