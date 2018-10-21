package com.asyncprogramming.demo.objects;

import java.util.concurrent.ThreadLocalRandom;

public enum Loot {
    NICE("You've got 1000 Dollars!"), NOT_BAD("You've got a limited edition figure of the Indian Jones statue"), BAD(
            "Oh no, this is a trap! 5-0 is coming.");

    private final String msg;

    Loot(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public static Loot randomLoot() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return Loot.values()[ThreadLocalRandom.current().nextInt(0, 3)];
    }
}