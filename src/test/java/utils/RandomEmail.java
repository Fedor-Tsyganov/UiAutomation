package utils;

import static constants.Const.baseEmailNames;
import static utils.RandomNumber.getRandomInt;

/**
 * Created by 2012mba4gb128gb on 1/31/17.
 */
public class RandomEmail {
    public static String getRandomEmail(){
        return baseEmailNames[getRandomInt(0,1)]+"+"+getRandomInt(200,3000)+"@gmail.com";
    }
}
