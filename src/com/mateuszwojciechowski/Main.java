package com.mateuszwojciechowski;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.Duration;
import java.time.Instant;

public class Main {
    public static System system;

    public static void main(String[] args) {
        // write your code here
        BufferedReader br = new BufferedReader(new InputStreamReader(java.lang.System.in));

        java.lang.System.out.println("------------------------");
        java.lang.System.out.println(" M/M/1 System Simulator ");
        java.lang.System.out.println("------------------------");

        java.lang.System.out.println("Choose lambda parameter: ");
        double lambda = 0;
        try {
            lambda = Double.valueOf(br.readLine());
        }
        catch(IOException e) {
            java.lang.System.out.println(e.getMessage());
        }

        java.lang.System.out.println("Choose mu parameter: ");
        double mu = 0;
        try {
            mu = Double.valueOf(br.readLine());
        }
        catch(IOException e) {
            java.lang.System.out.println(e.getMessage());
        }

        java.lang.System.out.println("Choose random generator seed: ");
        int seed = 0;
        try {
            seed = Integer.valueOf(br.readLine());
        } catch (IOException e) {
            java.lang.System.out.println(e.getMessage());
        }

        RandomGenerator.setSeed(seed);
        system = new System(lambda, mu);

        //Simulation loop
        while (Statistics.getNumberOfEvents() < 20000) {
            /*
            1. Process the current event.
            2. Generate new events.
             */
            Event currentEvent = System.getNextEvent();

            java.lang.System.out.println("-------------------------------");
            java.lang.System.out.println("Current event: " + currentEvent.getEventType());
            java.lang.System.out.println("Current time: " + currentEvent.getEventStartTime());

            currentEvent.process();

            if(currentEvent.getEventType() == Event.EventType.ARRIVAL) {
                long nextArrivalTime = RandomGenerator.getNextExpDist(System.getLambda());
                System.addEvent(new Event(Instant.ofEpochMilli(currentEvent.getEventStartTime().toEpochMilli() + nextArrivalTime), Event.EventType.ARRIVAL));
            }

//            try {
//                long timeToNextEvent = Math.abs(Duration.between(currentEvent.getEventStartTime(), System.getNextEventStartTime()).toMillis());
//                //calculate number of new events
//                long arrivalTime = 0;
//                int numberOfArrivals = 0;
//                Instant eventTime = currentEvent.getEventStartTime();
//                do {
//                    arrivalTime += RandomGenerator.getNextExpDist(System.getLambda());
//                    if (arrivalTime < timeToNextEvent) {
//                        numberOfArrivals++;
//                        System.addEvent(new Event(Instant.ofEpochMilli(eventTime.toEpochMilli() + arrivalTime), Event.EventType.ARRIVAL));
//                    }
//                } while (arrivalTime < timeToNextEvent);
//
//                java.lang.System.out.println("Number of new events: " + numberOfArrivals);
//            }
//            catch (NullPointerException e) {
//                Instant eventTime = Instant.ofEpochMilli(currentEvent.getEventStartTime().toEpochMilli() + RandomGenerator.getNextExpDist(System.getLambda()));
//                System.addEvent(new Event(eventTime, Event.EventType.ARRIVAL));
//            }
//            finally {
                System.setPreviousEventTime(currentEvent.getEventStartTime());
                java.lang.System.out.println("Average number of events in system: " + Statistics.getAverageNumberInSystem());
                java.lang.System.out.println("Average number of waiting tasks in system: " + Statistics.getAverageNumberOfWaitingTasks());
                java.lang.System.out.println("Average service time: " + Statistics.getAverageServiceTime());
                java.lang.System.out.println("Average time to service: " + Statistics.getAverageTimeToService());
                java.lang.System.out.println("Average event time: " + Statistics.getAverageEventTime());
                java.lang.System.out.println("Handled events: " + Statistics.getNumberOfEvents());
//            }
        }

        java.lang.System.out.println("----------------------");
        java.lang.System.out.println(" Theoretical results: ");
        java.lang.System.out.println("----------------------");
        java.lang.System.out.println("Average number of events in system: " + System.getRho()/(1-System.getRho()));
        java.lang.System.out.println("Average number of waiting tasks in system: " + Math.pow(System.getRho(), 2)/(1-System.getRho()));
        java.lang.System.out.println("Average service time: " + 1000/(System.getMu()-System.getLambda()));
        java.lang.System.out.println("Average time to service: " + System.getRho()*1000/(System.getMu()-System.getLambda()));
        java.lang.System.out.println("Average event time: " + Statistics.getAverageEventTime());
    }
}
