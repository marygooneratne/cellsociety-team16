package Visualization;

import javafx.animation.Timeline;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.LongProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/** set up the bottom portion of the UI that includes pause, play, speed, and cycle interval */

public class BuildOptions {
    private static int MIN_FRAMES = 1;
    private static int MAX_FRAMES = 10;
    private static int TICK_MARKS = 1;
    private static int SLIDER_SETX=200;
    private static int SLIDER_SETY=75;
    private static String PAUSE_LABEL = "pause";
    private static String PLAY_LABEL = "play";
    private static String SPEED_LABEL = "speed: frames/sec";
    private static Group myRoot;

    public BuildOptions(Group optionsRoot, Timeline anim, double framesPerS){
        myRoot = optionsRoot;
        //make buttons
        Button pause = new Button(PAUSE_LABEL);
        Button play = new Button(PLAY_LABEL);

        pause.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent ae) {
                anim.pause();
            }
        });

        play.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent ae) {
                anim.play();
            }
        });

//        //something for slider
//        speed.setBlockIncrement(1);
//        speed.valueProperty().addListener((Observable, oldValue, newValue) ->{
//            int i = newValue.intValue();
//        });

//        TextField fileInput = new TextField("Please write a valid filename");

        //add to optionsRoot to get to window
        myRoot.getChildren().add(pause);
        myRoot.getChildren().add(play);

        //define layout
        pause.setLayoutX(0);
        pause.setLayoutY(0);
        play.setLayoutX(0);
        play.setLayoutY(100);
    }

    public Group getRoot(){
        return myRoot;
    }
}
