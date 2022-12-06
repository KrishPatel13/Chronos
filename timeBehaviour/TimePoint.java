package timeBehaviour;

import java.time.LocalDateTime;


/**
 * A TimePoint Class, a concrete class for TimeBehaviour with deadline.
 */
public class TimePoint implements TimeBehaviour
{
    private final LocalDateTime deadlineTime; // the deadline of the TimeBehaviour object.


    /**
     * Constructor for initializing the TimePoint timebehavior with the given deadline time.
     *
     * @param time LocalDateTime
     */
    public TimePoint(LocalDateTime time)
    {
        this.deadlineTime = time;
    }


    /**
     * To get the deadline time of an TimeBehaviour object.
     *
     * @return LocalDateTime deadline of the event.
     */
    @Override
    public LocalDateTime getTime()
    {
        return this.deadlineTime;
    }


    /**
     * To check if the timebehaviour is still in-time or not.
     *
     * @param time the time to check of the timeBehaviour is still in time or not.
     * @return boolean true if the timebehaviour is still within the deadline, false otherwise.
     */
    @Override
    public boolean inTime(LocalDateTime time)
    {
        return this.deadlineTime.toLocalDate().equals(time.toLocalDate());
    }
}
