package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.io.FileNotFoundException;


public class App extends Application {
    SideBox leftSide;
    SideBox rightSide;
    HBox mainView = new HBox(50);
    ParametersBox parametersBox = new ParametersBox();


    public void init() throws Exception {
        super.init();
    }

    //creating stage and scene

    @Override
    public void start(Stage stage) {
        makeView();
        Scene scene = new Scene(mainView, 1500, 800);
        stage.setScene(scene);
        stage.setTitle("Zuzinka did it");
        stage.show();
    }

    //placing proper box on the screen

    public void makeView() {
        Button start = new Button("Press to start");
        start.setDefaultButton(true);

        VBox firstBox = new VBox(20);
        firstBox.getChildren().addAll(parametersBox.getParameters(), start);
        firstBox.setAlignment(Pos.CENTER);
        mainView.getChildren().add(firstBox);
        mainView.setAlignment(Pos.CENTER);

        start.setOnAction(value ->  {
            try {
                uploadData();
            }catch(IllegalArgumentException e){
                e.printStackTrace();
                System.exit(1);
            }
            mainView.getChildren().clear();
            mainView.getChildren().addAll(leftSide.getSideBox(), rightSide.getSideBox());
            mainView.setAlignment(Pos.CENTER);

        });
    }

    //uploading data from parameters menu

    public void uploadData(){
        try {
            AbstractWorldMap map2 = new NotExtendedMap(parametersBox.getHeight(), parametersBox.getWidth(),
                    parametersBox.getJungleRation(),parametersBox.getStartEnergy(), parametersBox.getPlantEnergy(),
                    parametersBox.getMoveEnergy());
            AbstractWorldMap map1 = new ExtendedMap(parametersBox.getHeight(), parametersBox.getWidth(),
                    parametersBox.getJungleRation(),parametersBox.getStartEnergy(), parametersBox.getPlantEnergy(),
                    parametersBox.getMoveEnergy());
            map1.setMagic(parametersBox.getMagicPreference(map1));
            map2.setMagic(parametersBox.getMagicPreference(map2));
            map1.placeAnimals(parametersBox.getAnimalsQuantity());
            map2.placeAnimals(parametersBox.getAnimalsQuantity());
            leftSide = new SideBox(map1);
            rightSide = new SideBox(map2);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

