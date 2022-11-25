package timeBehaviour;

import java.time.LocalDateTime;

public interface TimeBehaviour {
    /**
     * To set the time to a specific time range/point.
     */
    public void setTime(TimeBehaviour t);

    public TimeBehaviour getTime();

}
