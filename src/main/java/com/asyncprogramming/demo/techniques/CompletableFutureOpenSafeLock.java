package com.asyncprogramming.demo.techniques;

import java.util.concurrent.CompletableFuture;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.asyncprogramming.demo.actions.BankRobberyActions;
import com.asyncprogramming.demo.objects.Loot;
import com.asyncprogramming.demo.objects.Thief;

public class CompletableFutureOpenSafeLock {

      private static final Logger log = LoggerFactory.getLogger(CompletableFutureOpenSafeLock.class);

      public Loot openSafeLock(final Thief thief, final String victim) {
        return CompletableFuture.supplyAsync(BankRobberyActions::unlockTheDoor)
          .thenCompose(isOpened ->
            CompletableFuture.supplyAsync(() -> BankRobberyActions.figureOutSafetyBoxNumber(victim))
              .thenCombineAsync(
                                CompletableFuture.supplyAsync(() -> BankRobberyActions.hackTheSecretPin(victim)),
                BankRobberyActions::openSafeLock
              ).exceptionally(e -> {
                log.error("Something went wrong: {} Run, run, run!!", e.getMessage());
                return Loot.BAD;
              }
            )
          ).thenApply(
            loot -> {
              log.info("{} gets the content of the safety box: '{}'", thief.getName(), thief.handleLoot(loot));
              return loot;
            }
          ).join();
      }
    }