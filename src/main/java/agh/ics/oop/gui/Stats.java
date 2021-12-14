package agh.ics.oop.gui;

import agh.ics.oop.AbstractWorldMap;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class Stats {
    VBox vbox;
    public Stats(AbstractWorldMap map){
        Text stata_1 = new Text();
        Text stata_2 = new Text();
        Text stata_3 = new Text();
        vbox = new VBox(10);
        vbox.getChildren().addAll(stata_1, stata_2, stata_2);
    }

    public VBox getStats(){
        return vbox;
    }
}
