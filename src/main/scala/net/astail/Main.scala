package net.astail

import com.typesafe.config.ConfigFactory
import org.slf4j.{Logger, LoggerFactory}

object Main {
  val logger: Logger = LoggerFactory.getLogger(this.getClass)
  val longRev = net.astail.Git.longHash
  val token = ConfigFactory.load.getString("astel_bot_discord")
  val rev = net.astail.Git.shortHash


  def main(args: Array[String]): Unit = {
    s"""
       | ==================== start log ====================
       |
       |    rev : $rev
       |
       | ===================================================
       """.stripMargin.split('\n').foreach(logger.info(_))
  }

  discord
}
