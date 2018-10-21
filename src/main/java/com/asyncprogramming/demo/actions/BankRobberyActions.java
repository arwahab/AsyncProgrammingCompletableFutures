package com.asyncprogramming.demo.actions;

import java.util.concurrent.ThreadLocalRandom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.asyncprogramming.demo.objects.Loot;

public class BankRobberyActions {

    private static final Logger log = LoggerFactory.getLogger(BankRobberyActions.class);
    private static final long DELAY_MS = 1000L;

    private BankRobberyActions() {
    }

    public static boolean unlockTheDoor() {
        log.info("Forcing the door...");
        delay(2000);
        log.info("Door unlocked!");
        return true;
    }

    public static int hackTheSecretPin(final String accountHolderName) {
        log.info("Hacking the pin of {}", accountHolderName);
        delay();
        final int pin = ((accountHolderName.hashCode() % 1000) + 1000);
        log.info("Pin hacked: {}", pin);
        return pin;
    }

    public static String figureOutSafetyBoxNumber(final String accountHolderName) {
        log.info("Figuring out the safety box number of {}", accountHolderName);
        delay();
        final String lock = "A" + ThreadLocalRandom.current().nextInt(100);
        log.info("Got the safety box number: {}", lock);
        return lock;
    }

    public static Loot openSafeLock(final String safetyBoxNumber, final int pin) {
        log.info("Opening the safe lock {} using the pin {}", safetyBoxNumber, pin);
        delay();
        log.info("Safety Box opened:");
        return Loot.randomLoot();
    }

    private static void delay() {
        delay(DELAY_MS);
    }

    private static void delay(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
