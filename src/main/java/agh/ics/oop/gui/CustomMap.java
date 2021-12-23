package agh.ics.oop.gui;

import agh.ics.oop.AbstractWorldMap;
import agh.ics.oop.Vector2d;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;



import java.io.FileNotFoundException;



public class CustomMap {
    GridPane grid = new GridPane();
    int size;
    AbstractWorldMap map;
    Vector2d rightUpCorner;
    Stats stats;

    public CustomMap(AbstractWorldMap mapValue, Stats stats) throws FileNotFoundException {
        this.map = mapValue;
        rightUpCorner = map.findingUpperCorner();
        int maxi = Math.max(map.getHeight(), map.getWidth());
        this.size = 300 / maxi;
        this.stats = stats;
        makeGrid();
    }

    public VBox getCustomMap(){
        return new VBox(grid);
    }

// updating map every day

    public void positionChanged() {
        Platform.runLater(() -> {
            try {
                makeGrid();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
    }

// making new map

    private void makeGrid() throws FileNotFoundException {
        grid.setGridLinesVisible(false);
        grid.getChildren().clear();
        grid.getColumnConstraints().clear();
        grid.getRowConstraints().clear();

        Label label1 = new Label("y/x");
        grid.add(label1, 0, 0, 1, 1);
        grid.getColumnConstraints().add(new ColumnConstraints(size));
        grid.getRowConstraints().add(new RowConstraints(size));
        GridPane.setHalignment(label1, HPos.CENTER);
        grid.setGridLinesVisible(true);


        for (int i = 0; i <= rightUpCorner.x; i++) {
            Label label2 = new Label(" " + i + " ");
            grid.add(label2, i + 1, 0, 1, 1);
            grid.getColumnConstraints().add(new ColumnConstraints(size));
            GridPane.setHalignment(label2, HPos.CENTER);
        }

        for (int i = 0; i <= rightUpCorner.y; i++) {
            Label label3 = new Label(" " + i + " ");
            grid.add(label3, 0, rightUpCorner.y - i + 1, 1, 1);
            grid.getRowConstraints().add(new RowConstraints(size));
            GridPane.setHalignment(label3, HPos.CENTER);
        }


        for (int i = 0; i <= rightUpCorner.x; i++) {
            for (int j = 0; j <= rightUpCorner.y; j++) {
                Vector2d pos = new Vector2d(i, j);
                GuiElementBox guiBox;

                if (map.objectAt(pos) != null && map.getAnimals().get(pos) != null && !map.getAnimals().get(pos).isEmpty()) {
                    map.getAnimals().get(pos).sort(new AbstractWorldMap.EnergyComp());
                    guiBox = new GuiElementBox(map.getAnimals().get(pos).get(0), this.size, this.stats);
                    grid.add(guiBox.getBox(), pos.x + 1,
                            rightUpCorner.y - pos.y + 1, 1, 1);

                }else if(map.isPlantThere(pos)){
                    guiBox = new GuiElementBox(map.getPlant(pos), this.size, this.stats);
                    grid.add(guiBox.getBox(), pos.x + 1,
                            rightUpCorner.y - pos.y + 1, 1, 1);
                }
            }
        }
    }
}


