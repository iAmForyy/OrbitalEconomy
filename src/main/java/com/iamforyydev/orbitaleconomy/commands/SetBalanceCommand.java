package com.iamforyydev.orbitaleconomy.commands;

import com.iamforyydev.orbitaleconomy.data.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class SetBalanceCommand
        implements CommandExecutor {


    @Override
    public boolean onCommand(
            CommandSender sender,
            Command command,
            String label,
            String[] args
    ) {

        if(sender instanceof ConsoleCommandSender){
            return true;
        }

        if(args.length == 0){
            sender.sendMessage(
                    ChatColor.RED+"Wrong argument, use /setbalance <player> <amount>"
            );
            return true;
        }

        Player targetPlayer = Bukkit.getPlayer(args[0]);
        if(targetPlayer == null){
            sender.sendMessage(
                    ChatColor.RED+"Target is null!"
            );
            return true;
        }

        try {

            PlayerData targetEconomy = PlayerData.getPlayer(targetPlayer);
            int amount = Integer.parseInt(args[1]);
            targetEconomy.setCoins(amount);
            sender.sendMessage(
                    ChatColor.GREEN+"Successful transaction!"
            );

        }catch (NumberFormatException exception){
            sender.sendMessage(
                    ChatColor.RED+"You must enter a valid number!"
            );
        }
        return true;
    }
}
