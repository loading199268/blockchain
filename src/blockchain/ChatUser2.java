package blockchain;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ChatUser2 extends Thread{
    @Override
    public void run()
    {
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(() ->
                Chat.addChatList("miner2 sent 10 VC to miner3", "miner2", "miner3"), 0, 500, TimeUnit.MILLISECONDS);
    }
}
