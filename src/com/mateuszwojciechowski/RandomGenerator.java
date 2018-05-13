package com.mateuszwojciechowski;

import java.util.Random;

/**
 * Class representing exponential distribution random numbers generator.
 */
public class RandomGenerator {
    /**
     * Instance of random numbers generator
     */
    private Random random;

    /**
     * Constructor of the generator
     * @param seed random numbers generator seed
     * @param lambda exponential distribution parameter
     */
    public RandomGenerator(int seed, double lambda) {
        random = new Random(seed);
    }

    /**
     * Function to generate exponential distribution random number.
     * @param parameter exponential distribution parameter
     * @return exponential distribution random number
     */
    public double getNextExpDist(double parameter) {
        return Math.log(random.nextDouble())/(-parameter);
    }
}
