package Model.Threads;

import Model.Session;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import static javafx.scene.input.KeyEvent.KEY_PRESSED;
import static javafx.scene.input.KeyEvent.KEY_RELEASED;

public class KeyPress implements Runnable {

    private Scene scene;

    private Session sp;

    private boolean shift;

    public KeyPress(Scene scene, Session sp) {
        this.scene = scene;
        this.sp = sp;
        this.shift = false;
    }


    @Override
    public void run() {

        //shifts();


        scene.addEventFilter(KeyEvent.ANY, keyEvent -> {
            //System.out.println(keyEvent);

            String s = keyEvent.getCode().toString();
            if (keyEvent.getCode() != KeyCode.SHIFT) {
            if (keyEvent.getEventType() == KEY_PRESSED) {

                    if (s.contains("DIGIT")) {
                        s = s.substring(5);
                    }


                    if (keyEvent.isShiftDown()) {
                        s.toUpperCase();
                    } else {
                        s = s.toLowerCase();
                    }


                    //char c = s.charAt(0);

                    System.out.println("key = " + s);

                    typechar(s);
                }
            }

        });


    }


    public synchronized void typechar (String c){

        Platform.runLater(()-> {
            System.out.println(sp.TypeCharacter(c, sp.getPlayerOne()));

        });
    }



}
