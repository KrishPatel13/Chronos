package timeBehaviour;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * A TimeBehaviour interface.
 */
public interface TimeBehaviour extends Serializable
{

    /**
     * To get the time of the TimeBehaviour object.
     *
     * @return LocalDateTime time
     */
    LocalDateTime getTime();


    /**
     * To check if the timebehaviour is still in-time or not.
     *
     * @param time the time to check of the timeBehaviour is still in time or not.
     * @return boolean true if the timebehaviour is still within the deadline, false otherwise.
     */
    boolean inTime(LocalDateTime time);
}
