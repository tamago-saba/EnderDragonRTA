package com.github.tsuoihito.enderdragonrta

import org.bukkit.ChatColor
import org.bukkit.scoreboard.Criteria
import org.bukkit.scoreboard.DisplaySlot
import org.bukkit.scoreboard.Scoreboard

fun startRTA(plugin: EnderDragonRTA) {
    plugin.startTime = System.currentTimeMillis()
}

fun stopRTA(plugin: EnderDragonRTA) {
    val startTime = plugin.startTime ?: return
    plugin.lastRTATime = System.currentTimeMillis() - startTime
    plugin.startTime = null
}

fun showScoreboard(plugin: EnderDragonRTA) {
    plugin.server.onlinePlayers.forEach { player ->
        getScoreboard(plugin)?.let { player.scoreboard = it }
    }
}

private fun getScoreboard(plugin: EnderDragonRTA): Scoreboard? {
    val scoreboardManager = plugin.server.scoreboardManager ?: return null
    val scoreboard = scoreboardManager.newScoreboard
    scoreboard.registerNewObjective("EnderDragonRTA", Criteria.DUMMY, "EnderDragonRTA").apply {
        displayName = "${ChatColor.AQUA}EnderDragonRTA"
        displaySlot = DisplaySlot.SIDEBAR
        getScore("${ChatColor.GOLD}${getElapsedString(plugin)}").score = 15
        getScore("${ChatColor.GRAY}+---------------------").score = 14
        getScore("${ChatColor.YELLOW}Overworld: ${ChatColor.GREEN}${getPlayerCount(plugin, "world")}").score = 13
        getScore("${ChatColor.RED}Nether: ${ChatColor.GREEN}${getPlayerCount(plugin, "world_nether")}").score = 12
        getScore(
            "${ChatColor.LIGHT_PURPLE}The End: ${ChatColor.GREEN}${
                getPlayerCount(
                    plugin,
                    "world_the_end"
                )
            }"
        ).score = 11
        getScore("${ChatColor.GRAY}---------------------+").score = 10
    }
    return scoreboard
}

private fun getElapsedString(plugin: EnderDragonRTA): String {
    val startTime = plugin.startTime
    val lastRTATime = plugin.lastRTATime ?: 0
    val elapsed = if (startTime == null) lastRTATime else System.currentTimeMillis() - startTime
    return "${elapsed / 1000 / 3600}h ${elapsed / 1000 % 3600 / 60}m ${elapsed / 1000 % 3600 % 60}s"
}

private fun getPlayerCount(plugin: EnderDragonRTA, world: String): Int {
    return plugin.server.getWorld(world)?.players?.size ?: 0
}
