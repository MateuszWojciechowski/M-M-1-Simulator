package com.mateuszwojciechowski;

import java.time.Duration;
import java.time.Instant;

/**
 * Class representing a single event in the system.
 */
public class Event implements Comparable {
    /**
     * Enum type representing event type - arrival or departure.
     */
    public enum EventType { ARRIVAL, DEPARTURE }

    /**
     * A point on the time-line where event occurs.
     */
    private Instant eventStartTime;
    /**
     * Event type - arrival or departure.
     */
    private EventType eventType;

    private long eventDuration;

    /**
     * Constructor of the event. If event type is ARRIVAL then it creates departure event.
     * @param startTime point in time when event occurs
     * @param eventType type of the event
     */
    public Event(Instant startTime, EventType eventType) {
        this.eventStartTime = startTime;
        this.eventType = eventType;
        java.lang.System.out.println("NEW EVENT - start time: " + startTime.toString() + ", event type: " + eventType);
    }

    public Event(Instant startTime, EventType eventType, long eventDuration) {
        this.eventStartTime = startTime;
        this.eventType = eventType;
        this.eventDuration = eventDuration;
        java.lang.System.out.println("NEW EVENT - start time: " + startTime.toString() + ", event type: " + eventType);
    }

    /**
     * Event start time getter.
     * @return event start time
     */
    public Instant getEventStartTime() {
        return eventStartTime;
    }

    public EventType getEventType() {
        return eventType;
    }

    //Comparable implementation
    public int compareTo(Object o) {
        Event event = (Event) o;
        if(this.getEventStartTime().isBefore(event.getEventStartTime()))
            return -1;
        else if(this.getEventStartTime().isAfter(event.getEventStartTime()))
            return 1;
        else return 0;
    }

    public void process() {
        System.decreaseTimeToEmptySystem(Math.abs(Duration.between(getEventStartTime(), System.getPreviousEventTime()).toMillis()));
        if(eventType == EventType.ARRIVAL) {
            /*
            1. Increase number of events in the system.
            2. Generate departure event.
             */
            System.increaseEventsInSystem();
            long newEventDuration = RandomGenerator.getNextExpDist(System.getMu());
            java.lang.System.out.println("Event duration: " + newEventDuration + "ms");
            System.addEvent(new Event(Instant.ofEpochMilli(eventStartTime.toEpochMilli() + newEventDuration + System.getTimeRemainingToEmptySystem()), EventType.DEPARTURE, newEventDuration));
            System.increaseTimeRemainingToEmptySystem(newEventDuration);
            Statistics.addToAverage(newEventDuration, System.getEventsInSystem());
        }
        else {
            /*
            1. Decrease number of events in the system.
            2. Decrease time remaining to empty system
             */
            System.decreaseEventsInSystem();
            System.decreaseTimeToEmptySystem(eventDuration);
        }
        java.lang.System.out.println("Events in system: " + System.getEventsInSystem());
    }
}
