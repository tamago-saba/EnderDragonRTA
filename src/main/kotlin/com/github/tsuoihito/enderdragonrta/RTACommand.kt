package com.github.tsuoihito.enderdragonrta

import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabExecutor

class RTACommand(private val plugin: EnderDragonRTA) : TabExecutor {
    override fun onTabComplete(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>
    ): List<String> {
        return when (args.size) {
            1 -> listOf("start", "stop").filter { it.startsWith(args[0]) }
            else -> emptyList()
        }
    }

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (args.size != 1) return false
        when (args[0]) {
            "start" -> startRTA(plugin)
            "stop" -> stopRTA(plugin)
        }
        return true
    }
}
