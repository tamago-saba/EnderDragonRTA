package com.github.tsuoihito.enderdragonrta

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerChangedWorldEvent

class CompleteListener(private val plugin: EnderDragonRTA) : Listener {
    @EventHandler
    fun onPlayerChangedWorld(event: PlayerChangedWorldEvent) {
        val to = event.player.location.world ?: return
        if (event.from.name == "world" && to.name == "world_the_end") {
            stopRTA(plugin)
        }
    }
}
