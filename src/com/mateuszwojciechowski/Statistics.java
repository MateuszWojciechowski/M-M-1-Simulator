package com.mateuszwojciechowski;

public class Statistics {
    private static int numberOfEvents = 0;
    private static double averageNumberInSystem = 0;
    private static double simulationTime = 0;

    public static void addToAverage(long duration, int state) {
        averageNumberInSystem += duration * state;
        simulationTime += duration;
    }
    public static double getAverageNumberInSystem() {
        return averageNumberInSystem/simulationTime;
    }

    public static double getAverageTime() {
        return simulationTime/numberOfEvents;
    }

    public static void increaseNumberOfEvents() {
        numberOfEvents++;
    }

    public static int getNumberOfEvents() {
        return numberOfEvents;
    }
}
