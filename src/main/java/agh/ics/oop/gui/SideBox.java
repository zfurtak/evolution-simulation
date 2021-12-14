package agh.ics.oop.gui;

import agh.ics.oop.AbstractWorldMap;
import javafx.application.Platform;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class SideBox {
    VBox mainBox;

    public SideBox(AbstractWorldMap map){
        HBox topBox = new TopBox(map).getTopBox();
        VBox chart = new VBox(getChart());
        HBox downBox = new Buttons(map).getButtons();
        mainBox = new VBox(topBox, chart, downBox);
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
