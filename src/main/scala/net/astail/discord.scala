package net.astail

import net.astail.Main.token
import net.astail.Main.rev
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.entities.{Activity, User}
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import net.dv8tion.jda.api.utils.Compression
import net.dv8tion.jda.api.utils.cache.CacheFlag

object discord {
  val setStatus = "rev: " + rev
  val builder = JDABuilder.createDefault(token)
  builder.disableCache(CacheFlag.MEMBER_OVERRIDES, CacheFlag.VOICE_STATE)
  builder.setBulkDeleteSplittingEnabled(false)
  builder.setCompression(Compression.NONE)
  builder.setActivity(Activity.playing(setStatus))
  builder.addEventListeners(new MessageListener)
  builder.build

  class MessageListener extends ListenerAdapter {
    override def onMessageReceived(event: MessageReceivedEvent): Unit = {
      def sendMessage(x: String) = event.getTextChannel.sendMessage(x).queue

      val message = event.getMessage.getContentDisplay
      val messageUser: User = event.getMember.getUser

      if (!event.getAuthor.isBot) {
        sendMessage(messageUser + ": " + message)
      }

    }
  }
}
