package agh.ics.oop;

import javafx.scene.image.Image;

public interface IMapElement {

    Vector2d getPosition();

    Image getImage();

    void addObserver(IPositionChangeObserver observer);

    void removeObserver(IPositionChangeObserver observer);

}
