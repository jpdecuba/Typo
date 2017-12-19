package Model;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.TimerTask;

class Time implements Serializable{
    private LocalDateTime time;

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public int difference(Time t){
        return (int) (this.time.getSecond() - t.time.getSecond());
    }
}

public class ComboTimer implements Serializable{
    private Time startTime = new Time();
    private Time endTime = new Time();

    private float constTime = 5;
    private float amountOfTime = 0;

    public ComboTimer() {
        startTime.setTime(LocalDateTime.now());
        amountOfTime = constTime;
    }

    public long DeltaTime(){
        return endTime.difference(startTime);
    }

    public void setStartTime(LocalDateTime startTime) {
        System.out.println("st: " + startTime);
        this.startTime.setTime(startTime);
    }

    public void setEndTime(LocalDateTime endTime) {
        System.out.println("Et: " + endTime);
        this.endTime.setTime(endTime);
    }

    public int getCombo(int combo){
        setEndTime(LocalDateTime.now());

        System.out.println("st: " + startTime);
        System.out.println("et: " + endTime);
        System.out.println("dt: " + DeltaTime());
        System.out.println("ct: " + constTime);
        System.out.println("at: " + amountOfTime);
        System.out.println("c: " + combo);

        if(DeltaTime() <= amountOfTime){
            combo++;
        }
        else{
            combo = 1;
        }
        amountOfTime = constTime - (0.4f * combo);
        return combo;
    }
}
