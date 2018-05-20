package com.mateuszwojciechowski;

import java.time.Duration;
import java.time.Instant;

public class Main {
    public static System system;

    public static void main(String[] args) {
	// write your code here
        system = new System(100, 101);

        //Simulation loop
        while(true) {
            /*
            1. Process the current event.
            2. Generate new events.
             */
            Event currentEvent = System.getNextEvent();
            java.lang.System.out.println("-------------------------------");

            if(currentEvent != null) {
                java.lang.System.out.println("Current event: " + currentEvent.getEventType());
                java.lang.System.out.println("Current time: " + currentEvent.getEventStartTime());
                currentEvent.process();
                Statistics.increaseNumberOfEvents();
                long timeFromLastEvent = Math.abs(Duration.between(currentEvent.getEventStartTime(), System.getPreviousEventTime()).toMillis());
                long numberOfNewEvents = Math.round(System.getLambda() * timeFromLastEvent / 1000);
                //java.lang.System.out.println("Time from last event: " + timeFromLastEvent + "ms");
                java.lang.System.out.println("Number of new events: " + numberOfNewEvents);
                System.setPreviousEventTime(currentEvent.getEventStartTime());
                Instant eventTime = currentEvent.getEventStartTime();
                for(int i = 0; i < numberOfNewEvents; i++) {
                    eventTime = Instant.ofEpochMilli(eventTime.toEpochMilli() + RandomGenerator.getNextExpDist(System.getLambda()));
                    System.addEvent(new Event(eventTime, Event.EventType.ARRIVAL));
                }
                java.lang.System.out.println("Average number of events in system: " + Statistics.getAverageNumberInSystem());
                java.lang.System.out.println("Average service time: " + Statistics.getAverageTime());
                java.lang.System.out.println("Handled events: " + Statistics.getNumberOfEvents());
            } else {
                Instant eventTime = Instant.ofEpochMilli(System.getPreviousEventTime().toEpochMilli() + RandomGenerator.getNextExpDist(System.getLambda()));
                System.addEvent(new Event(eventTime, Event.EventType.ARRIVAL));
            }
        }
    }
}
