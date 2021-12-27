package agh.ics.oop.gui;

import agh.ics.oop.AbstractWorldMap;
import agh.ics.oop.NotExtendedMap;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;


// handling text fields for parameters

public class ParametersBox {
    VBox vbox;
    SingleParametersBox height = new SingleParametersBox("Height", "10");
    SingleParametersBox width = new SingleParametersBox("Width", "10");
    SingleParametersBox animalsQuantity = new SingleParametersBox("How many animals do you want on the beginning", "20");
    SingleParametersBox startEnergy = new SingleParametersBox("Start amount of energy for each animal", "50");
    SingleParametersBox plantEnergy = new SingleParametersBox("Amount of energy given by a plant", "10");
    SingleParametersBox moveEnergy = new SingleParametersBox("Amount of energy taken after a move", "1");
    SingleParametersBox jungleRatio = new SingleParametersBox("Part of the map taken by a jungle", "0.2");
    CheckBox notExtendedMap;
    CheckBox extendedMap;


    public ParametersBox() {
        Label title = new Label("Enter parameters, por favor");
        title.setFont(new Font("Arial", 24));
        VBox.setMargin(title, new Insets(0, 0, 30, 0));

        notExtendedMap = new CheckBox("Magic evolution for map with bounds");
        extendedMap = new CheckBox("Magic evolution for map without bounds");



        vbox = new VBox(10);
        vbox.getChildren().addAll(title, height.getSingleBox(), width.getSingleBox(), animalsQuantity.getSingleBox(),
                startEnergy.getSingleBox(), plantEnergy.getSingleBox(), moveEnergy.getSingleBox(), jungleRatio.getSingleBox(),
                notExtendedMap, extendedMap);
        vbox.setAlignment(Pos.CENTER);
    }

    public VBox getParameters(){
        return vbox;
    }

    public int getHeight(){
        return Integer.parseInt(height.getValue());
    }

    public int getWidth(){
        return Integer.parseInt(width.getValue());
    }

    public int getAnimalsQuantity(){
        return Integer.parseInt(animalsQuantity.getValue());
    }

    public int getStartEnergy(){
        return Integer.parseInt(startEnergy.getValue());
    }

    public int getPlantEnergy(){
        return Integer.parseInt(plantEnergy.getValue());
    }

    public int getMoveEnergy(){
        return Integer.parseInt(moveEnergy.getValue());
    }

    public double getJungleRation(){
        return Double.parseDouble(jungleRatio.getValue());
    }

    public boolean getMagicPreference(AbstractWorldMap map){
        if(map instanceof NotExtendedMap)
            return notExtendedMap.isSelected();
        else
            return extendedMap.isSelected();
    }

}

