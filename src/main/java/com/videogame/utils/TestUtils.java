package com.videogame.utils;

import java.util.Random;

public class TestUtils {
    public static String getRandomValue(){
        Random random = new Random();
        int randomInt = random.nextInt(10000);
        return Integer.toString(randomInt);
    }

    public static int getRandomValueInt() {
        Random random = new Random();
        int randomInt = random.nextInt(1000);
        return randomInt;
    }

}
