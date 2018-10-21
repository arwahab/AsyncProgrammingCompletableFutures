package com.asyncprogramming.demo.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.asyncprogramming.demo.objects.Loot;
import com.asyncprogramming.demo.objects.Thief;
import com.asyncprogramming.demo.techniques.CompletableFutureOpenSafeLock;
import com.asyncprogramming.demo.techniques.FutureOpenSafeLock;
import com.asyncprogramming.demo.techniques.SingleThreadOpenSafeLock;

public class App {
/**
 * Main application: runs both approaches in sequence
 */

  private static final Logger log = LoggerFactory.getLogger(App.class);

  public static void main(String[] args) throws Exception {

    log.info("\n\n SINGLE THREAD ====");
    final Loot singleThreadLoot = new SingleThreadOpenSafeLock().openSafeLockFunctional(Thief.ULLOO, "Mr. Toomuchmoney");
    log.info("App got the loot {}", singleThreadLoot);

    log.info("\n\n PLAIN FUTURES ====");
    final Loot plainFutureLoot = new FutureOpenSafeLock().openSafeLock(Thief.KA, "Ms. Greedy");
    log.info("App got the loot {}", plainFutureLoot);

    log.info("\n\n COMPLETABLE FUTURE ====");
    final Loot completableFutureLoot = new CompletableFutureOpenSafeLock().openSafeLock(Thief.PATTA, "Sr. Carapapas");
    log.info("App got the loot {}", completableFutureLoot);

  }


}
