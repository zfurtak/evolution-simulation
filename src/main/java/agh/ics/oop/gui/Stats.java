package agh.ics.oop.gui;

import agh.ics.oop.AbstractWorldMap;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Stats {
    VBox vbox;
    public Stats(AbstractWorldMap map){
        Label title = new Label("Statystyki");
        title.setFont(new Font("Arial", 18));
        VBox.setMargin(title, new Insets(10, 0, 10, 0));

        Text stata_1 = new Text("statatatata1");
        Text stata_2 = new Text("statatata2");
        Text stata_3 = new Text("statatatata3");
        vbox = new VBox(10);
        vbox.getChildren().addAll(title, stata_1, stata_2, stata_3);
    }

    public VBox getStats(){
        return vbox;
    }
}
