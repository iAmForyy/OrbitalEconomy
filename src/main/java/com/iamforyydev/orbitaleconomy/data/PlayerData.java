package com.iamforyydev.orbitaleconomy.data;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class PlayerData {

    private static final HashMap<UUID, PlayerData> playerDataHashMap = new HashMap<>();
    private final UUID uuid;

    private int coins;

    public PlayerData(
            Player player
    ) {
        this.uuid = player.getUniqueId();
        this.coins = 0;
    }

    public static PlayerData getPlayer(
            Player player
    ) {
        UUID uuid = player.getUniqueId();
        if (playerDataHashMap().containsKey(uuid)) {
            return playerDataHashMap().get(uuid);
        }

        PlayerData playerData = new PlayerData(player);
        playerDataHashMap().put(uuid, playerData);
        return playerData;
    }

    public void addCoins(
            int amount
    ){
        setCoins(getCoins()+amount);
    }

    public void removeCoins(
            int amount
    ) {
        setCoins(getCoins()-amount);
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public Player getPlayer(

    ) {
        return Bukkit.getPlayer(getUuid());
    }

    public UUID getUuid() {
        return uuid;
    }

    public static HashMap<UUID, PlayerData> playerDataHashMap() {
        return playerDataHashMap;
    }
}
