package agh.ics.oop.gui;

import agh.ics.oop.AbstractWorldMap;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class TopBox {
    HBox mainBox;
    public TopBox(AbstractWorldMap map){
        VBox stats = new Stats(map).getStats();
        Text text = new Text("Tu bedzie mapa ale mam tylko dwie rece i brak checi");
        VBox.setMargin(text, new Insets(10, 0, 0, 0));
        VBox mapBox = new VBox(text); //new CustomMap(map).getMap()
        mainBox = new HBox(30);
        mainBox.getChildren().addAll(stats, mapBox);
    }

    public HBox getTopBox(){
        return mainBox;
    }


}
