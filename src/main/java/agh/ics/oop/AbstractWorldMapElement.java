package agh.ics.oop;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractWorldMapElement implements IMapElement{
    protected  Vector2d position;
    protected List<IPositionChangeObserver> observers = new ArrayList<>();


    abstract public Image getImage();


    @Override
    public Vector2d getPosition() {
        return this.position;
    }


    public void addObserver(IPositionChangeObserver observer){
        observers.add(observer);
    }

    protected void notify(Vector2d oldPos,Vector2d newPos, Animal animal){
        for (IPositionChangeObserver obs: observers) {
            obs.positionChanged(oldPos, newPos, animal);
        }
    }

    public void removeObserver(IPositionChangeObserver observer){
        observers.remove(observer);
    }
}

