package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractWorldMapElement implements IMapElement{
    protected  Vector2d position;
    protected List<IPositionChangeObserver> observers = new ArrayList<>();

    public String toString(){
        if( this instanceof Animal)
            return ((Animal) this).getOrient().shortString();
        return "*";
    }

    public boolean isAt(Vector2d pos) {
        return this.position.equals(pos);
    }

    abstract public String getPath();

    @Override
    public Vector2d getPosition() {
        return this.position;
    }

    public void addObserver(IPositionChangeObserver observer){
        observers.add(observer);
    }

    protected void notify(Vector2d oldPos,Vector2d newPos ){
        for (IPositionChangeObserver obs: observers) {
            obs.positionChanged(oldPos, newPos);
        }
    }

    public void removeObserver(IPositionChangeObserver observer){
        observers.remove(observer);
    }
}

