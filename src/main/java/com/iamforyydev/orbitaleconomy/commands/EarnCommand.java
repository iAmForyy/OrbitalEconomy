package com.iamforyydev.orbitaleconomy.commands;

import com.iamforyydev.orbitaleconomy.Cooldown;
import com.iamforyydev.orbitaleconomy.OrbitalEconomy;
import com.iamforyydev.orbitaleconomy.data.PlayerData;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class EarnCommand
        implements CommandExecutor {

    private final OrbitalEconomy plugin = OrbitalEconomy.getInstance();

    @Override
    public boolean onCommand(
            CommandSender sender,
            Command command,
            String label,
            String[] args
    ) {
        if(sender instanceof ConsoleCommandSender){
            sender.sendMessage(
                    ChatColor.RED+"Only players!"
            );
            return true;
        }

        Player player = (Player) sender;
        Cooldown cooldown = plugin.getCooldown();

        if (cooldown.checkCooldown(player.getUniqueId())) {
            PlayerData playerData = PlayerData.getPlayer(player);
            int random = ThreadLocalRandom.current().nextInt(1, 5);
            playerData.addCoins(random);
            sender.sendMessage(
                    ChatColor.GREEN + "Successful transaction! You received " + random + " coins"
            );

            cooldown.setCooldown(player.getUniqueId(), System.currentTimeMillis());
        } else {
            long time = System.currentTimeMillis() - cooldown.getCooldown(player.getUniqueId());
            player.sendMessage(
                    ChatColor.RED +
                            "You must wait "+ (cooldown.COOLDOWN - TimeUnit.MILLISECONDS.toSeconds(time)) + "s to use this again!"
            );
        }


        return true;
    }
}
