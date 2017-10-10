package Model.Threads;

import Model.Session;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import javax.xml.stream.EventFilter;
import javax.xml.stream.events.XMLEvent;
import java.util.Observable;
import java.util.Observer;

import static javafx.scene.input.KeyEvent.KEY_PRESSED;
import static javafx.scene.input.KeyEvent.KEY_RELEASED;

public class KeyPress implements Runnable, Observer {

    private Scene scene;

    private Session sp;

    private EventHandler keypressevent;


    public KeyPress(Scene scene, Session sp) {
        this.scene = scene;
        this.sp = sp;
        this.sp.addObserver(this);



    }


    @Override
    public void run() {





        keypressevent = new EventHandler<KeyEvent>(){

            @Override
            public void handle(KeyEvent keyEvent){

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

                        //System.out.println("key = " + s);

                        typechar(s);
                    }
                }

            }


        };




        scene.addEventFilter(KeyEvent.ANY, keypressevent);



    }

    public synchronized void typechar(String c) {


            sp.TypeCharacter(c, sp.getPlayerOne());

    }


    @Override
    public void update(Observable o, Object arg) {

        scene.removeEventFilter(KeyEvent.ANY, keypressevent);
        scene.removeEventHandler(KeyEvent.ANY, keypressevent);
    }
}
