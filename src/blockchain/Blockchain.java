package blockchain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Blockchain {
    private int id;
    private long timeStamp;
    private long magicNumber;
    private String hashPreviousBlock;
    private String hashThisBlock;
    private static int lastId = 0;
    private int countZero;
    private long elapsed;
    private String minerNumber;



    private List<String> blockChat = new ArrayList<>();

    private String output;

    static List<Blockchain> list = new ArrayList<>();
    MagicNumber generateMagicNumber = new MagicNumber();





    public Blockchain(int countZero, String minerNumber) {
        this.countZero = countZero;
        this.minerNumber = minerNumber;
        this.id = list.get(list.size()-1).getId() + 1;
        this.timeStamp = new Date().getTime();
        this.magicNumber = generateMagicNumber.getRandomMagicNumber(countZero);
        this.hashPreviousBlock = list.get(list.size() - 1).getHashThisBlock();
        this.hashThisBlock = Hashing.applySha256(String.valueOf(magicNumber));
        this.blockChat = Chat.getChatList();
        Chat.clearChatList();
    }

    public Blockchain(int id, int countZero, String minerNumber) {
        this.countZero = countZero;
        this.minerNumber = minerNumber;
        this.id = id;
        this.timeStamp = new Date().getTime();
        this.magicNumber = generateMagicNumber.getRandomMagicNumber(countZero);
        this.hashPreviousBlock = "0";
        this.hashThisBlock = Hashing.applySha256(String.valueOf(magicNumber));
    }

    public static synchronized void addBlock(Blockchain blockchain){
        if (list.isEmpty()) {
            if (Action.elapsed[Action.elapsed.length-1] < 15000 && Action.countZero < 4) {
                Action.countZero++;
                blockchain.setOutput("N was increased to " + Action.countZero);
            } else if (Action.elapsed[Action.elapsed.length-1] > 60000) {
                Action.countZero--;
                blockchain.setOutput("N was decreased by " + Action.countZero);
            }else {
                blockchain.setOutput("N stays the same");
            }
            list.add(blockchain);
            Coin.addCoin(blockchain.getMinerNumber(), 100);
        }else if(blockchain.getHashPreviousBlock().equals(list.get(list.size()-1).getHashThisBlock())
                && list.size()<15
                &&blockchain.getTimeStamp()>list.get(list.size()-1).getTimeStamp()) {
            if(blockchain.countZero == Action.countZero) {
                if (Action.elapsed[Action.elapsed.length-1] < 15000 && Action.countZero < 4) {
                    Action.countZero++;
                    blockchain.setOutput("N was increased to " + Action.countZero);
                } else if (Action.elapsed[Action.elapsed.length-1] > 60000) {
                    Action.countZero--;
                    blockchain.setOutput("N was decreased by " + Action.countZero);
                }else {
                    blockchain.setOutput("N stays the same");
                }
                blockchain.setId(list.get(list.size()-1).getId() + 1);
                list.add(blockchain);
                Coin.addCoin(blockchain.getMinerNumber(), 100);
            }else {
                createBlock(Action.countZero, blockchain.minerNumber);
            }
        }
    }

    public String getMinerNumber() {
        return minerNumber;
    }


    public int getId() {
        return id;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public String getHashPreviousBlock() {
        return hashPreviousBlock;
    }

    public String getHashThisBlock() {
        return hashThisBlock;
    }

    public String getOutput() {
        return output;
    }

    public List<String> getBlockChat() {
        return blockChat;
    }

    public long getMagicNumber() {
        return magicNumber;
    }

    public void setOutput(String output) {
        this.output = output;
    }
    public void setId(int id) {
        this.id = id;
    }

    public static void createBlock(int countZero, String minerNumber) {
        if (Blockchain.list.isEmpty()) {
            Blockchain blockchain = new Blockchain(1, countZero, minerNumber);
            addBlock(blockchain);
        }else {
            Blockchain blockchain = new Blockchain(countZero, minerNumber);
            addBlock(blockchain);
        }
    }

    public void outputOfFiveBlocks(long[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.println("Block:");
            System.out.println("Created by: " + list.get(i).getMinerNumber());
            System.out.println(list.get(i).getMinerNumber() + " gets 100 VC");
            System.out.println("Id: " + list.get(i).getId());
            System.out.println("Timestamp: " + list.get(i).getTimeStamp());
            System.out.println("Magic number: " + magicNumber);
            System.out.println("Hash of the previous block:");
            System.out.println(list.get(i).getHashPreviousBlock());
            System.out.println("Hash of the block:");
            System.out.println(list.get(i).getHashThisBlock());
            if (list.get(i).blockChat.isEmpty()) {
                System.out.println("Block data: no messages");
            }else {
                System.out.println("Block data:");
                for (String massage : list.get(i).getBlockChat()) {
                    System.out.println(massage);
                }
            }
            System.out.println("Block was generating for " + array[i] + " seconds");
            System.out.println(list.get(i).getOutput());
            System.out.println();
        }
    }
}
