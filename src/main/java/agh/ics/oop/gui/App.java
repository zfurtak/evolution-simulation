package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.io.FileNotFoundException;

import static java.lang.System.out;

public class App extends Application {
    AbstractWorldMap map1;
    AbstractWorldMap map2;
    SideBox leftSide;
    SideBox rightSide;
    GridPane mainView = new GridPane();
    ParametersBox parametersBox = new ParametersBox();
    public int counter1 = 0;
    public int counter2 = 0;


    public void init() throws Exception {
        super.init();


    }

    @Override
    public void start(Stage stage) throws FileNotFoundException {
        makeGrid();
        Scene scene = new Scene(mainView, 1500, 800);
        stage.setScene(scene);
        stage.setTitle("Zuzinka did it");
        stage.show();

    }

    public void drawNewMap(AbstractWorldMap map){
        map.avgEnergy = map.getAnimalLinkedList().stream().mapToInt(Animal :: getEnergy).sum() / map.animalsQuantity;
        map.avgChildrenNo = map.getAnimalLinkedList().stream().mapToInt(Animal :: getChildrenNo).sum() / map.animalsQuantity;
        map.avgLifeTime = ((map.deadAnimalsNo-map.newDeathsNo) * map.avgLifeTime + map.newLifeTimeData) / Math.max(map.deadAnimalsNo, 1);
        if(map.equals(map1)) {
            this.leftSide.topBox.customMap.positionChanged();
            this.leftSide.getChart(this.counter1);
            counter1 ++;
        }
        else{
            this.rightSide.topBox.customMap.positionChanged();
            this.rightSide.getChart(this.counter2);
            counter2 ++;
        }
    }



    public void makeGrid() throws FileNotFoundException {
        Button start = new Button("Press to start");
        start.setDefaultButton(true);

        VBox firstBox = new VBox(20);
        firstBox.getChildren().addAll(parametersBox.getParameters(), start);
        firstBox.setAlignment(Pos.CENTER);
        mainView.add(firstBox, 0, 0, 1, 1);
        mainView.setAlignment(Pos.CENTER);

        start.setOnAction(value ->  {
            uploadData();
            SimulationEngine engine1 = new SimulationEngine(this.map1, this);
            SimulationEngine engine2 = new SimulationEngine(this.map2, this);
            Thread thread1 = new Thread(engine1);
            Thread thread2 = new Thread(engine2);
            try {
                leftSide = new SideBox(map1, thread1, this.counter1);
                rightSide = new SideBox(map2, thread2, this.counter2);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            HBox secondBox = new HBox(10);
            secondBox.getChildren().addAll(leftSide.getSideBox(), rightSide.getSideBox());
            mainView.getChildren().clear();

            mainView.add(secondBox, 0, 0, 1, 1);

            mainView.setAlignment(Pos.CENTER);
            thread1.start();
            thread2.start();
        });
    }

    public ParametersBox getParametersBox() {
        return parametersBox;
    }

    public void uploadData(){
        this.map1 = new NotExtendedMap(parametersBox.getHeight(), parametersBox.getWidth(),
                parametersBox.getJungleRation(),parametersBox.getStartEnergy(), parametersBox.getPlantEnergy(),
                parametersBox.getMoveEnergy());
        this.map2 = new ExtendedMap(parametersBox.getHeight(), parametersBox.getWidth(),
                parametersBox.getJungleRation(),parametersBox.getStartEnergy(), parametersBox.getPlantEnergy(),
                parametersBox.getMoveEnergy());
    }
}