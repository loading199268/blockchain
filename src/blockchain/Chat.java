package blockchain;

import java.util.ArrayList;
import java.util.List;

public class Chat {
    static List<String> chatList = new ArrayList<>();
    public static int count = 0;

    public static synchronized List<String> getChatList() {
        count++;
        return chatList;
    }

    public static synchronized void addChatList(String massage, String miner1, String miner2) {
        if (Coin.addCoin(miner1, miner2, 10)) {
            chatList.add(massage);
        }
    }

    public static synchronized void clearChatList() {
        chatList = new ArrayList<>();
    }
}
