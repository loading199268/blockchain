package blockchain;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ChatUser3 extends Thread{
    @Override
    public void run()
    {
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(() ->
                Chat.addChatList("miner3 sent 10 VC to miner1", "miner3", "miner1"), 0, 1000, TimeUnit.MILLISECONDS);
    }
}
