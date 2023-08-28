package org.piasnik

import discord4j.core.DiscordClient
import discord4j.core.event.domain.message.MessageCreateEvent

object DiscordClient {
    @JvmStatic
    fun main(args: Array<String>) {
        val client = DiscordClient.create("yourDiscordToken")
        val gateway = client.login().block() ?: return

        gateway.on(MessageCreateEvent::class.java).subscribe { event: MessageCreateEvent ->
            val message = event.message
            if (!message.author.get().isBot) {
                val channel = message.channel.block()
                channel.createMessage("Otrzymałem twoją wiadomość ${message.author.get().username} :) ")
                    .block()
            }
        }

        gateway.onDisconnect().block()
    }
}