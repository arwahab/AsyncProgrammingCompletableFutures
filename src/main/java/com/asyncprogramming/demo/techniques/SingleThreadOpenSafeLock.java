package com.asyncprogramming.demo.techniques;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.asyncprogramming.demo.actions.BankRobberyActions;
import com.asyncprogramming.demo.objects.Loot;
import com.asyncprogramming.demo.objects.Thief;

import java.util.stream.Stream;

public class SingleThreadOpenSafeLock {

    private static final Logger log = LoggerFactory.getLogger(SingleThreadOpenSafeLock.class);

    public Loot openSafeLock(final Thief thief, final String victim) {
        BankRobberyActions.unlockTheDoor();

        final String safetyBoxNumber = BankRobberyActions.figureOutSafetyBoxNumber(victim);
        final int pin = BankRobberyActions.hackTheSecretPin(victim);

        final Loot loot = BankRobberyActions.openSafeLock(safetyBoxNumber, pin);
        log.info("{} gets the content of the safety box: '{}'", thief.getName(), thief.handleLoot(loot));
        return loot;
    }

    public Loot openSafeLockFunctional(final Thief thief, final String victim) {
        return Stream.of(BankRobberyActions.unlockTheDoor())
                .map((ignore) -> BankRobberyActions.figureOutSafetyBoxNumber(victim))
                .map((safetyBoxNumber) -> BankRobberyActions.openSafeLock(safetyBoxNumber,
                        BankRobberyActions.hackTheSecretPin(victim)))
                .peek((loot -> log.info("{} gets the content of the safety box: '{}'", thief.getName(),
                        thief.handleLoot(loot))))
                .findFirst().get();
    }
}