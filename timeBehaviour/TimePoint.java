package timeBehaviour;


import java.time.LocalDateTime;

public class TimePoint implements TimeBehaviour{
    private LocalDateTime deadlineTime;


    public TimePoint(LocalDateTime time)
    {
        this.deadlineTime = time;
    }

    /**
     * To set the time to a specific time range/point.
     *
     * @param time the new deadline time to set.
     */
    @Override
    public void setTime(LocalDateTime time) {
        this.deadlineTime = time;
    }

    /**
     * @param time1
     * @param time2
     */
    @Override
    public void setTime(LocalDateTime time1, LocalDateTime time2) {
        return;
    }

    /**
     * To get the deadline time of an event.
     * @return LocalDateTime deadline of the event.
     */
    @Override
    public LocalDateTime getTime() {
        return this.deadlineTime;
    }

    @Override
    public boolean inTime(LocalDateTime time) {
        if (this.deadlineTime.toLocalDate().equals(time.toLocalDate())){
            return true;
        }
        return false;
    }
}
