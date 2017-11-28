package UnitTest;

import Model.Difficulty;
import Model.Opportunity;
import Model.Player;

import java.util.Observable;
import java.util.Observer;

public class TestObserver implements Observer {

    public boolean success;

    @Override
    public void update(Observable observable, Object o) {

        if (o.getClass() == Opportunity.class) {
            Opportunity opp = (Opportunity)o;
            success = true;
        }
    }
}
