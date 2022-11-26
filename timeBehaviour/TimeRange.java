package timeBehaviour;

import java.time.LocalDateTime;

public class TimeRange implements TimeBehaviour {

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    public TimeRange(LocalDateTime start_time_block, LocalDateTime end_time_block) {
        this.startTime = start_time_block;
        this.endTime = end_time_block;
    }


    public LocalDateTime getStartTime() {
        return this.startTime;
    }

    public LocalDateTime getEndTime() {
        return this.endTime;
    }


    /**
     * To extend the end_time of the event.
     *
     * @param time
     */
    @Override
    public void setTime(LocalDateTime time) {
        this.endTime = time;
    }



    /**
     * To set the time to a specific time range/point.
     */
    @Override
    public void setTime(LocalDateTime time_start, LocalDateTime time_end) {
        this.startTime = time_start;
        this.endTime = time_end;
    }


    /**
     * @return
     */
    @Override
    public LocalDateTime getTime() {
        return this.startTime;
    }
}
