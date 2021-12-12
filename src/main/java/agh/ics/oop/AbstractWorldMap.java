package agh.ics.oop;

import java.util.LinkedHashMap;


public abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver {
    protected LinkedHashMap<Vector2d, AbstractWorldMapElement> natures = new LinkedHashMap<>();


    public String toString() {
        MapVisualizer mapVisualizer = new MapVisualizer(this);
        return mapVisualizer.draw(findingLowerCorner(), findingUpperCorner());
    }


    public void positionChanged(Vector2d oldPos, Vector2d newPos) {
        AbstractWorldMapElement jim = this.natures.get(oldPos);
        this.natures.remove(oldPos);
        this.natures.put(newPos, jim);
    }


    public boolean place(Animal animal) {
        if (canMoveTo(animal.getPosition())) {
            this.natures.put(animal.getPosition(), animal);
            animal.addObserver(this);
            return true;
        } else {
            throw new IllegalArgumentException("Can't place animal on" + animal.getPosition());
        }
    }

    abstract public Vector2d findingUpperCorner();

    abstract public Vector2d findingLowerCorner();

    public AbstractWorldMapElement getElement(Vector2d pos) {
        return this.natures.get(pos);
    }

    public LinkedHashMap<Vector2d, AbstractWorldMapElement> getAnimals() {
        return this.natures;
    }

    public boolean isOccupied(Vector2d position) {
        return natures.get(position) != null;
    }

    public Object objectAt(Vector2d position) {
        return natures.get(position);
    }

    public boolean canMoveTo(Vector2d position) {
        return !(objectAt(position) instanceof Animal);
    }
}