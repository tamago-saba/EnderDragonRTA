package com.github.tsuoihito.enderdragonrta

import org.bukkit.scheduler.BukkitRunnable

class ScoreboardTask(private val plugin: EnderDragonRTA) : BukkitRunnable() {
    override fun run() {
        showScoreboard(plugin)
    }
}
