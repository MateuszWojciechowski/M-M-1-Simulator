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

    /**
     * Constructor of the event. If event type is ARRIVAL then it creates departure event.
     * @param startTime point in time when event occurs
     * @param eventType type of the event
     */
    public Event(Instant startTime, EventType eventType) {
        this.eventStartTime = startTime;
        this.eventType = eventType;

        //if ARRIVAL then generate DEPARTURE event
        if(this.eventType == EventType.ARRIVAL) {
            //TODO implement
        }
    }

    /**
     * Event start time getter.
     * @return event start time
     */
    public Instant getEventStartTime() {
        return eventStartTime;
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
}
