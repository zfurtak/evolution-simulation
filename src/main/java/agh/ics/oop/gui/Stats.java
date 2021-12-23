package agh.ics.oop.gui;

import agh.ics.oop.AbstractWorldMap;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class Stats {
    VBox vbox;
    Text chosenGenome;
    Text mostCommonGenome;
    Text magicOn;

//class containing information about genome and magic mode

    public Stats(){
        Label title = new Label("Stats");
        title.setFont(new Font("Arial", 18));
        VBox.setMargin(title, new Insets(10, 0, 10, 0));

        mostCommonGenome = new Text("");
        mostCommonGenome.setWrappingWidth(300);
        chosenGenome = new Text("Click on the animal to show its genome");
        chosenGenome.setWrappingWidth(300);
        magicOn = new Text("");
        magicOn.setWrappingWidth(300);

        vbox = new VBox(10);
        vbox.getChildren().addAll(title, mostCommonGenome, chosenGenome, magicOn);
    }

    public void updateMagicCounter(int x){
        magicOn.setText("Magic mode has been initialized "+x+" times so far.");
    }

    public void updateGenome(AbstractWorldMap map){
        mostCommonGenome.setText("Most common genome: " + map.mostCommonGenome.toString());
    }


    public VBox getStats(){
        return vbox;
    }
}
