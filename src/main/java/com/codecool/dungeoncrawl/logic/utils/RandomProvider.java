package com.codecool.dungeoncrawl.logic.utils;

import java.util.Random;

public class RandomProvider {
    private static final Random RANDOM = new Random();

    public static int getRandomNumberOfRange(int min, int max){
        return RANDOM.nextInt(max - min) + min;
    }

    public static int getRandomNumber(int upper){
        return RANDOM.nextInt(upper);
    }
}
