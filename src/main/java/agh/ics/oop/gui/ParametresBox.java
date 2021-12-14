package agh.ics.oop.gui;

import agh.ics.oop.AbstractWorldMapElement;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ParametresBox {
    VBox vbox;

    public ParametresBox() {
        Label title = new Label("Enter parameters, por favor");
        title.setFont(new Font("Arial", 24));
        VBox.setMargin(title, new Insets(0, 0, 30, 0));

        VBox height = new SingleParametersBox("Height", "10").getSingleBox();

        VBox width = new SingleParametersBox("Width", "10").getSingleBox();

        VBox animalsQuantity = new SingleParametersBox("How many animals do you want on the beginning", "5").getSingleBox();

        VBox startEnergy = new SingleParametersBox("Start amount of energy for each animal", "5").getSingleBox();

        VBox plantEnergy = new SingleParametersBox("Amount of energy given by a plant", "10").getSingleBox();

        VBox moveEnergy = new SingleParametersBox("Amount of energy taken after a move", "1").getSingleBox();

        VBox jungleRatio = new SingleParametersBox("Part of the map taken by a jungle", "20%").getSingleBox();

        CheckBox notExtendedMap = new CheckBox("Magic evolution for map with bounds");
        CheckBox ExtendedMap= new CheckBox("Magic evolution for map without bounds");


        vbox = new VBox(10);
        vbox.getChildren().addAll(title, height ,width, animalsQuantity, startEnergy,
                plantEnergy, moveEnergy, jungleRatio, notExtendedMap, ExtendedMap);
        //vbox.setPrefWidth(100);
        vbox.setAlignment(Pos.CENTER);
    }

    public VBox getParameters(){
        return vbox;
    }
}

