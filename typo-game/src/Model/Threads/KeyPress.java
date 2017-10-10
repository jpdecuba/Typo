package Model.Threads;

import Model.Session;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;

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

        shifts();


        scene.setOnKeyReleased(event -> {

            System.out.println("key = " + event.getCode().toString());

            String s = event.getCode().toString();
            switch (event.getCode()) {
                case SHIFT:
                    shift = false;
                    break;
            }

            if (s.contains("DIGIT")) {
                s = s.substring(5);
            }


            if (shift) {
                s.toUpperCase();
            } else {
                s = s.toLowerCase();
            }


            //char c = s.charAt(0);

            System.out.println("key = " + s);

            typechar(s);
            shift = false;

        });

    }


    public synchronized void typechar (String c){

        Platform.runLater(()-> {
            System.out.println(sp.TypeCharacter(c, sp.getPlayerOne()));

        });
    }

    private void shifts (){

        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case SHIFT:
                    shift = true;
                    break;
            }

        });


    }

}
