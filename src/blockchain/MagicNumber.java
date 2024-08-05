package blockchain;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static blockchain.Hashing.applySha256;

public class MagicNumber {
    Random random = new Random();

    public Long getRandomMagicNumber(int prevZero) {
        String regex = "^[0]{" + prevZero + "}";
        boolean nextTry = true;
        long magicNumber = 0;
        StringBuilder stringBuilder = new StringBuilder();
        while (nextTry) {
            magicNumber = random.nextLong();
            stringBuilder = new StringBuilder(applySha256(String.valueOf(magicNumber)));
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(stringBuilder);
            if (matcher.find()) {
                nextTry = false;
            }
        }
        return magicNumber;
    }
}
