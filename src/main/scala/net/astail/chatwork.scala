package net.astail

import com.typesafe.config.ConfigFactory
import play.api.libs.json.Json

import scala.sys.process._

object chatwork {
  val chatworkApi = ConfigFactory.load.getString("chatwork_token")
  val chatworkRoomId = ConfigFactory.load.getString("chatwork_room_id")

  def post(message: String): String = {

    val cmd = Seq(
      "curl",
      "-s",
      "-X",
      "POST",
      "-H",
      s"X-ChatWorkToken: ${chatworkApi}",
      "-d",
      s"body=${message}",
      s"https://api.chatwork.com/v2/rooms/${chatworkRoomId}/messages")

    val resultJson = Json.parse(cmd.!!)
    val resultMap: Map[String, String] = resultJson.as[Map[String, String]]

    s"https://www.chatwork.com/#!rid${chatworkRoomId}-${resultMap("message_id")}"
  }

}
