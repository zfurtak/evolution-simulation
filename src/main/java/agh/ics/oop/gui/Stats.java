package agh.ics.oop.gui;

import agh.ics.oop.AbstractWorldMap;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class Stats {
    VBox vbox;
    Text chosenGenome;
    Text mostCommonGenome;

    public Stats(){
        Label title = new Label("Statystyki");
        title.setFont(new Font("Arial", 18));
        VBox.setMargin(title, new Insets(10, 0, 10, 0));

        mostCommonGenome = new Text("");
        mostCommonGenome.setWrappingWidth(300);
        chosenGenome = new Text("Click on the animal to show its genome");
        chosenGenome.setWrappingWidth(300);
        Text stata_3 = new Text("");

        vbox = new VBox(10);
        vbox.getChildren().addAll(title, mostCommonGenome, chosenGenome, stata_3);


    }


    public void updateGenome(AbstractWorldMap map){
        mostCommonGenome.setText("Most common genome: " + map.mostCommonGenome.toString());
    }


    public VBox getStats(){
        return vbox;
    }
}
