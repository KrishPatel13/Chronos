package event;

import org.junit.jupiter.api.Test;
import timeBehaviour.TimePoint;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * A Unit Test Class for Event.java.
 */
class EventTest
{
    /**
     * A Unit Test for Event.complete().
     */
    @Test
    void completeTest()
    {
        Event e = new Event("Event1", "testing", 100, new TimePoint(LocalDateTime.now()));

        e.complete();

        assertEquals(0, Event.getObserverList().size());
    }
}
