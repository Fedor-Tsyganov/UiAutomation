package utils;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by 2012mba4gb128gb on 1/31/17.
 */
public class RandomNumber {

    public static int getRandomInt(int min, int max){
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }
}
