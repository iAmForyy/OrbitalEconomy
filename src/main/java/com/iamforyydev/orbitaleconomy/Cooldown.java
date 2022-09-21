package com.iamforyydev.orbitaleconomy;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class Cooldown {

    private final Map<UUID, Long> cooldownHashMap = new HashMap<>();
    public final int COOLDOWN = 10;

    public long getCooldown(
            UUID uuid
    ) {
        return cooldownHashMap.getOrDefault(uuid, 0L);
    }

    public void setCooldown(
            UUID uuid,
            long time
    ) {
        if(time < 1){
            cooldownHashMap.remove(uuid);
        } else {
            cooldownHashMap.put(uuid, time);
        }
    }

    public boolean checkCooldown(
        UUID uuid
    ) {
        long timeleft = System.currentTimeMillis() - getCooldown(uuid);
        return TimeUnit.MILLISECONDS.toSeconds(timeleft) >= COOLDOWN;
    }






}
