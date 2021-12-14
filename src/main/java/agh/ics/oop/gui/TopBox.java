package agh.ics.oop.gui;

import agh.ics.oop.AbstractWorldMap;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class TopBox {
    HBox mainBox;
    public TopBox(AbstractWorldMap map){
        VBox stats = new Stats(map).getStats();
        LineChart<Number, Number> lc = new LineChart<>(new NumberAxis(), new NumberAxis());
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
    }
}
