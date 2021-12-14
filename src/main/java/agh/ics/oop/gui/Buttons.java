package agh.ics.oop.gui;

import agh.ics.oop.AbstractWorldMap;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Buttons {
    HBox mainBox;

    public Buttons(AbstractWorldMap map){
        Button startStop = new Button("Start/Stop");
        Button save = new Button("Save stats");
        Button genome = new Button("Show animals with most common genome");
        mainBox = new HBox(genome, save, startStop);

        //place for buttons action
    }

    public HBox getButtons(){
        return mainBox;
    }


}
