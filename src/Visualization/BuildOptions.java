package Visualization;

import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.beans.property.LongProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.text.Text;

/** set up the bottom portion of the UI that includes pause, play, speed, and cycle interval */

public class BuildOptions {
    private static int MIN_FRAMES = 1;
    private static int MAX_FRAMES = 10;
    private static int TICK_MARKS = 1;
    private static String PAUSE_LABEL = "pause";
    private static String PLAY_LABEL = "play";
    private static String SPEED_LABEL = "speed: frames/sec";
    private static String CYCLE_LABEL = "CYCLE: ";
    private static Group myRoot;

    public BuildOptions(Group optionsRoot, Timeline anim, double framesPerS, LongProperty cycleNum){
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

        //make slider with label
        Slider speed = new Slider(MIN_FRAMES, MAX_FRAMES, framesPerS);
        speed.setMajorTickUnit(TICK_MARKS);
        speed.setShowTickMarks(true);
        speed.setShowTickLabels(true);
        Label sliderLabel = new Label(SPEED_LABEL);
        sliderLabel.setLayoutX(200);
        sliderLabel.setLayoutY(75);
        myRoot.getChildren().add(sliderLabel);
        sliderLabel.setLabelFor(speed);

        //something for slider

        Text cycle = new Text();
        cycle.textProperty().bind(Bindings.createStringBinding(() -> (CYCLE_LABEL + cycleNum.get())));

        //add to optionsRoot to get to window
        myRoot.getChildren().add(pause);
        myRoot.getChildren().add(play);
        myRoot.getChildren().add(speed);
        myRoot.getChildren().add(cycle);

        //define layout
        pause.setLayoutX(0);
        pause.setLayoutY(0);
        play.setLayoutX(0);
        play.setLayoutY(100);
        speed.setLayoutX(200);
        speed.setLayoutY(100);
        cycle.setX(200);
        cycle.setY(0);
    }

    public Group getRoot(){
        return myRoot;
    }
}
