package com.mateuszwojciechowski;

import java.util.Collections;
import java.util.LinkedList;

/**
 * Class representing a system with its specification.
 */
public class System {
    /**
     * Inflow intensity.
     */
    private double lambda;
    /**
     * Service intensity.
     */
    private double mu;
    /**
     * System usage rate.
     */
    private double rho;

    /**
     * Lambda getter.
     * @return lambda parameter
     */
    public double getLambda() {
        return lambda;
    }

    /**
     * Mu getter.
     * @return mu parameter
     */
    public double getMu() {
        return mu;
    }

    /**
     * Rho getter.
     * @return rho parameter
     */
    public double getRho() {
        return rho;
    }

    /**
     * Sequence of the events in the system.
     */
    private LinkedList<Event> eventList;

    /**
     * Function which puts a new event to the list of events
     * @param newEvent new event on the time-line
     */
    public void addEvent(Event newEvent) {
        boolean success = false;
        //Go through list, find an element which is "bigger" and insert new event in that place.
        for(Event e : eventList) {
            if(e.compareTo(newEvent) > 0) {
                eventList.add(eventList.indexOf(e), newEvent);
                success = true;
                break;
            }
        }
        //If new event is the biggest, insert it in the end of the list.
        if(!success)
            eventList.add(newEvent);
    }

    /**
     * Function to get the next event from the list.
     * @return next event
     */
    public Event getNextEvent() {
        Event event = eventList.getFirst();
        eventList.removeFirst();
        return event;
    }

    /**
     * Queue size.
     */
    private int queueSize;
//TODO statistics
    /**
     * Constructor with lambda and mu parameters
     * @param lambda inflow intensity
     * @param mu service intensity
     */
    public System(double lambda, double mu) {
        this.lambda = lambda;
        this.mu = mu;
        rho = lambda/mu;
        queueSize = 0;
    }
}
