package blockchain;

import java.util.HashMap;
import java.util.Map;

public class Coin {
    static Map<String, Integer> collectionCoin = new HashMap<>();


    public static synchronized boolean addCoin(String minerName1,String minerName2, int coin) {
        if (collectionCoin.containsKey(minerName1)) {
            if (collectionCoin.get(minerName1) - coin >= 0) {
                if (collectionCoin.containsKey(minerName2)) {
                    collectionCoin.put(minerName2, collectionCoin.get(minerName2) + coin);
                } else {
                    collectionCoin.put(minerName2, coin);
                }
                collectionCoin.put(minerName1, collectionCoin.get(minerName1) - coin);
                return true;
            }
        }
        return false;
    }

    public static synchronized boolean addCoin(String minerName, int coin) {
        if (collectionCoin.containsKey(minerName)) {
            collectionCoin.put(minerName, collectionCoin.get(minerName) + coin);
            return true;
        }else {
            collectionCoin.put(minerName, coin);
            return true;
        }
    }
}
