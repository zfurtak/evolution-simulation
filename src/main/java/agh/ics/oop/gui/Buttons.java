package agh.ics.oop.gui;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

import static java.lang.System.exit;

public class Buttons {
    HBox mainBox;
    private final SideBox side;

    // buttons placed under the line chart

    public Buttons(Thread thread, SideBox sideBox){
        this.side = sideBox;
        Button stop = new Button("Stop");
        Button start = new Button("Start");
        Button save = new Button("Save stats");
        Button genome = new Button("Most common genome");
        Button exit = new Button("Finish simulation");
        mainBox = new HBox(genome, save, start, stop, exit);
        mainBox.setSpacing(10);

        exit.setOnAction(event -> exit(0));
        stop.setOnAction(event -> {
            thread.suspend();
            genome.setOnAction(event1 -> {
                try {
                    this.side.showMostCommonGenome();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            });
            save.setOnAction(event1 -> {
                try {
                    this.side.engine.makeCSVFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        });
        start.setOnAction(event -> {
            thread.resume();
            genome.setOnAction(null);
        });


    }


    public HBox getButtons(){
        return mainBox;
    }


}
