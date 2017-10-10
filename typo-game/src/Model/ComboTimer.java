package Model;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.TimerTask;

public class ComboTimer extends TimerTask {
    private LocalDateTime startTime = null;
    private LocalDateTime endTime = null;

    public ComboTimer(){

    }

    @Override
    public void run() {
        //do something after a specified time

    }

    public long DeltaTime(){
        Duration end = Duration.ZERO;
        Duration start = Duration.ZERO;
        end.plusSeconds(endTime.getSecond());
        start.plusSeconds(startTime.getSecond());
        return end.minus(start).getSeconds();
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }
}
