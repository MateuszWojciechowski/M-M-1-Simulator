package com.mateuszwojciechowski;

import java.util.Random;

/**
 * Class representing exponential distribution random numbers generator.
 */
public class RandomGenerator {
    /**
     * Instance of random numbers generator
     */
    private static Random random = new Random();

    /**
     * Function to generate exponential distribution random number.
     * @param parameter exponential distribution parameter
     * @return exponential distribution random number
     */
    public static long getNextExpDist(double parameter) {
        long number = Math.round(Math.log(random.nextDouble())/(-parameter) * 1000);
        return number;
    }
}
