package com.asyncprogramming.demo.techniques;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.asyncprogramming.demo.actions.BankRobberyActions;
import com.asyncprogramming.demo.objects.Loot;
import com.asyncprogramming.demo.objects.Thief;

public class FutureOpenSafeLock {
    private static final Logger log = LoggerFactory.getLogger(FutureOpenSafeLock.class);

    public Loot openSafeLock(final Thief thief, final String victim) throws Exception {
        final ExecutorService executorService = Executors.newFixedThreadPool(4);
        final Future<Boolean> doorUnlockFuture = executorService.submit(BankRobberyActions::unlockTheDoor);
        doorUnlockFuture.get();

        final Future<String> safetyBoxNumberFuture = executorService
                .submit(() -> BankRobberyActions.figureOutSafetyBoxNumber(victim));
        final Future<Integer> pinRetrieverFuture = executorService
                .submit(() -> BankRobberyActions.hackTheSecretPin(victim));

        final Future<Loot> lootFuture = executorService
                .submit(() -> BankRobberyActions.openSafeLock(safetyBoxNumberFuture.get(), pinRetrieverFuture.get()));

        executorService.shutdown();
        final Loot loot = lootFuture.get();
        log.info("{} gets the content of the safety box: '{}'", thief.getName(), thief.handleLoot(loot));

        return loot;
    }
}
