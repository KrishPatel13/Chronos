package timeBehaviour;

import java.time.LocalDateTime;


/**
 * A TimeRange Class, a concrete class for TimeBehaviour with a range of start time and end time.
 */
public class TimeRange implements TimeBehaviour
{
    private final LocalDateTime startTime; // the start time of the TimeBehaviour object.

    private final LocalDateTime endTime; // the end time of the TimeBehaviour object.


    /**
     * Constructor for initializing a TimeRange timebehaviour with given range of time.
     *
     * @param start_time_block the start time of the range.
     * @param end_time_block the end time of the range.
     */
    public TimeRange(LocalDateTime start_time_block, LocalDateTime end_time_block)
    {
        this.startTime = start_time_block;
        this.endTime = end_time_block;
    }


    /**
     * To get the Start time of the timebehaviour instance.
     *
     * @return startTime
     */
    public LocalDateTime getStartTime()
    {
        return this.startTime;
    }


    /**
     * To get the End Time of the timebehaviour instance.
     *
     * @return endTime
     */
    public LocalDateTime getEndTime()
    {
        return this.endTime;
    }


    /**
     * To get the time of the TimeBehaviour object.
     *
     * @return LocalDateTime time
     */
    @Override
    public LocalDateTime getTime()
    {
        return this.startTime;
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
        System.out.println(this.startTime.isBefore(time));
        System.out.println(this.endTime.isAfter(time));
        return (this.startTime.isBefore(time) && this.endTime.isAfter(time)) ||
                this.startTime.toLocalDate().equals(time.toLocalDate()) ||
                this.endTime.toLocalDate().equals(time.toLocalDate());
    }
}
