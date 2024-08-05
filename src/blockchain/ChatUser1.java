package blockchain;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ChatUser1 extends Thread{
    @Override
    public void run()
    {
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(() ->
                Chat.addChatList("miner1 sent 10 VC to miner2", "miner1", "miner2"), 0, 500, TimeUnit.MILLISECONDS);
    }
}
