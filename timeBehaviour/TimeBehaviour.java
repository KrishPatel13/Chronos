package timeBehaviour;

import java.io.Serializable;
import java.time.LocalDateTime;

public interface TimeBehaviour extends Serializable {
    /**
     * To set the time to a specific time range/point.
     */
    public void setTime(LocalDateTime time);

    public void setTime(LocalDateTime time1, LocalDateTime time2);

    public LocalDateTime getTime();

    public boolean inTime(LocalDateTime time);
}
