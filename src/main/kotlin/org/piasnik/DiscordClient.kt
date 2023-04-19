package org.piasnik

import discord4j.core.DiscordClient
import discord4j.core.event.domain.message.MessageCreateEvent


object DiscordClient {
    @JvmStatic
    fun main(args: Array<String>) {
        val client = DiscordClient.create("yourDiscordToken")
        val gateway = client.login().block() ?: return

        gateway.on(MessageCreateEvent::class.java).subscribe { event ->
            println(event.message)
            val input = event.message
            client.getChannelById(input.channelId).createMessage("Otrzymałem twoją wiadomość :) :${input.content}")
                .block()
        }

        gateway.onDisconnect().block()
    }
}