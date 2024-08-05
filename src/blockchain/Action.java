package blockchain;

import java.time.Duration;
import java.time.Instant;

public class Action {
    public static long[] elapsed = new long[15];

    static int countZero = 0;
    public void startHashing() throws InterruptedException {

        Blockchain blockchain = new Blockchain(1, countZero, "miner1");




        Miners miners = null;

        for (int i = 0; i < elapsed.length; i++) {
            Instant start = Instant.now();

            miners = new Miners();
            miners.startMiners();
            ChatUser1 chatUser1 = new ChatUser1();
            ChatUser2 chatUser2 = new ChatUser2();
            ChatUser3 chatUser3 = new ChatUser3();
            chatUser1.start();
            chatUser2.start();
            chatUser3.start();


            Instant finish = Instant.now();


            elapsed[i] = Duration.between(start, finish).toSeconds();

        }
        miners.executor.shutdown();
        Thread.sleep(8000);
        blockchain.outputOfFiveBlocks(elapsed);
    }

}
