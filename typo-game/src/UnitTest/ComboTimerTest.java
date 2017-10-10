package UnitTest;


import Model.ComboTimer;

import static org.junit.Assert.assertEquals;

public class ComboTimerTest {
    ComboTimer ct = new ComboTimer();

    @org.junit.Test
    public void DeltaTime() throws Exception {
        ct.DeltaTime();
    }
}
