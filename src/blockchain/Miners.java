package blockchain;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Miners {
    ExecutorService executor = Executors.newFixedThreadPool(POOL_SIZE);

    private final static int POOL_SIZE = 2;
    private final static int NUMBER_OF_TASKS = 15;


    public void startMiners() {
        for (int i = 0; i < NUMBER_OF_TASKS; i++) {
            String minerNumber = "miner" + i;
            executor.submit(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                Blockchain.createBlock(Action.countZero, minerNumber);

            });
        }
    }
}
