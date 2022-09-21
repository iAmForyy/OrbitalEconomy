package com.iamforyydev.orbitaleconomy.commands;

import com.iamforyydev.orbitaleconomy.data.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class GiveCommand
        implements CommandExecutor {


    @Override
    public boolean onCommand(
            CommandSender sender,
            Command command,
            String label,
            String[] args
    ) {
        if(sender instanceof ConsoleCommandSender){
            return false;
        }

        if(args.length == 0){
            sender.sendMessage(
                    ChatColor.RED+"Wrong argument, use /give <player> <amount>"
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

        if (args[0].equalsIgnoreCase(sender.getName())) {
            sender.sendMessage(
                    ChatColor.RED+"You can't send money to yourself!"
            );
            return true;
        }

        try {

            Player player = (Player) sender;
            PlayerData
                    targetEconomy = PlayerData.getPlayer(targetPlayer),
                    playerEconomy = PlayerData.getPlayer(player);
            int amount = Integer.parseInt(args[1]);
            if(playerEconomy.getCoins() <= amount){

                targetEconomy.addCoins(amount);
                playerEconomy.removeCoins(amount);
                sender.sendMessage(
                        ChatColor.GREEN + "Successful transaction!"
                );
            } else{
                sender.sendMessage(
                        ChatColor.RED+"You don't have enough money!"
                );
            }

            return true;
        }catch (NumberFormatException exception){
            sender.sendMessage(
                    ChatColor.RED+"You must enter a valid number!"
            );
        }

        return true;
    }
}
