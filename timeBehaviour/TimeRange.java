package timeBehaviour;

import java.time.LocalDateTime;

public class TimeRange implements TimeBehaviour {

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    /**
     * To set the time to a specific time range/point.
     *
     * @param t
     */
    @Override
    public void setTime(LocalDateTime s, LocalDateTime e) {
        this.startTime = s;

        this.endTime = e;
    }

    /**
     * @return
     */
    public LocalDateTime getStartTime() {
        return this.startTime;
    }

    public LocalDateTime getEndTime() {
        return this.endTime;
    }
}
