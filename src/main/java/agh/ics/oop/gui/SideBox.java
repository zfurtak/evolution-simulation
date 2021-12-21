package agh.ics.oop.gui;

import agh.ics.oop.AbstractWorldMap;
import javafx.application.Platform;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.FileNotFoundException;

public class SideBox {
    VBox mainBox;
    TopBox topBox;

    public SideBox(AbstractWorldMap map, Thread thread) throws FileNotFoundException {
        topBox = new TopBox(map);
        HBox box = topBox.getTopBox();
        VBox chart = new VBox(getChart());
        //VBox chart = new VBox(new Label("tu bedzie wykres"));
        HBox downBox = new Buttons(map, thread, true).getButtons();
        mainBox = new VBox(box, chart, downBox);
    }

    public VBox getSideBox(){
        return mainBox;
    }

    public LineChart getChart(){
        LineChart<Number, Number> chart = new LineChart<>(new NumberAxis(), new NumberAxis());
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        chart.getData().add(series);

        new Thread(() -> {
            try {
                Thread.sleep(5000);
                for (int i = 0; i < 15; i++) {
                    int finalI = i;
                    Platform.runLater(() -> series.getData().add(new XYChart.Data<>(1 + finalI, 1 + finalI)));
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        return chart;
    }
}
