package com.iamforyydev.orbitaleconomy.commands;

import com.iamforyydev.orbitaleconomy.data.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class BalanceCommand
        implements CommandExecutor {

    @Override
    public boolean onCommand(
            CommandSender sender,
            Command command,
            String label,
            String[] args
    ) {
        if (sender instanceof ConsoleCommandSender) {
            sender.sendMessage("Console no!");
            return false;
        }

        if(args.length == 0){
            Player player = (Player) sender;
            PlayerData playerData = PlayerData.getPlayer(player);

            sender.sendMessage(
                    ChatColor.GREEN+"Your Balance: "+playerData.getCoins()
            );

            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);
        if(target == null){
            sender.sendMessage(ChatColor.RED+"Player null");
            return true;
        }

        PlayerData targetEconomy = PlayerData.getPlayer(target);
        sender.sendMessage(
                ChatColor.GREEN+"Balance: "+targetEconomy.getCoins()
        );
        return true;
    }
}
