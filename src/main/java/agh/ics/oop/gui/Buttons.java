package agh.ics.oop.gui;

import agh.ics.oop.AbstractWorldMap;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

import static java.lang.System.exit;

public class Buttons {
    HBox mainBox;
    Boolean flag;

    public Buttons(AbstractWorldMap map, Thread thread, Boolean flag){
        Button stop = new Button("Stop");
        Button start = new Button("Start");
        Button save = new Button("Save stats");
        Button genome = new Button("Most common genome");
        Button exit = new Button("Finish simulation");
        mainBox = new HBox(genome, save, start, stop, exit);
        mainBox.setSpacing(10);
        this.flag = flag;

        exit.setOnAction(event -> exit(0));
        stop.setOnAction(event -> thread.suspend());
        start.setOnAction(event -> thread.resume());

    }

    public HBox getButtons(){
        return mainBox;
    }


}
