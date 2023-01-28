package com.github.tsuoihito.enderdragonrta

import org.bukkit.plugin.java.JavaPlugin

class EnderDragonRTA : JavaPlugin() {
    var startTime: Long? = null
    var lastRTATime: Long? = null

    override fun onEnable() {
        getCommand("rta")?.setExecutor(RTACommand(this))
        server.pluginManager.registerEvents(CompleteListener(this), this)
        ScoreboardTask(this).runTaskTimer(this, 0, 20)
    }

    override fun onDisable() {
    }
}
