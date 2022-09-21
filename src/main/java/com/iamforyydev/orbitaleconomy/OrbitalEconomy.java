package com.iamforyydev.orbitaleconomy;

import com.iamforyydev.orbitaleconomy.commands.BalanceCommand;
import com.iamforyydev.orbitaleconomy.commands.SetBalanceCommand;
import com.iamforyydev.orbitaleconomy.commands.GiveCommand;
import com.iamforyydev.orbitaleconomy.commands.EarnCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class OrbitalEconomy extends JavaPlugin {

    private static OrbitalEconomy instance;
    public static OrbitalEconomy getInstance(){
        return instance;
    }

    private Cooldown cooldown;

    @Override
    public void onEnable() {
        instance = this;
        this.cooldown = new Cooldown();

        commandHandler();
    }

    private void commandHandler(
    ) {
        getCommand("balance").setExecutor(new BalanceCommand());
        getCommand("setbalance").setExecutor(new SetBalanceCommand());
        getCommand("give").setExecutor(new GiveCommand());
        getCommand("earn").setExecutor(new EarnCommand());

    }

    public Cooldown getCooldown() {
        return cooldown;
    }
}
