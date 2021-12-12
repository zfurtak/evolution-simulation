package agh.ics.oop;

import java.util.LinkedHashMap;


public abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver{
    protected LinkedHashMap<Vector2d, AbstractWorldMapElement> natures = new LinkedHashMap<>();
    protected final MapBoundary boundaryMap = new MapBoundary();


    abstract public Vector2d findingUpperCorner();

    abstract public Vector2d findingLowerCorner();

    public String toString() {
        MapVisualizer mapVisualizer = new MapVisualizer(this);
        return mapVisualizer.draw(findingLowerCorner(), findingUpperCorner());
    }

    public AbstractWorldMapElement getElement(Vector2d pos){
        return this.natures.get(pos);
    }

    public LinkedHashMap<Vector2d, AbstractWorldMapElement> getAnimals(){
        return this.natures;
    }

    public void positionChanged(Vector2d oldPos, Vector2d newPos){
        AbstractWorldMapElement jim = this.natures.get(oldPos);
        this.natures.remove(oldPos);
        this.natures.put(newPos, jim);
    }

    public boolean canMoveTo(Vector2d position) {
        return !(objectAt(position) instanceof Animal);
    }


    public boolean place(Animal animal) {
        if (canMoveTo(animal.getPosition())) {
            this.natures.put(animal.getPosition(), animal);
            boundaryMap.positionChanged(new Vector2d(9999,9999), animal.getPosition());
            animal.addObserver(this);
            animal.addObserver(boundaryMap);
            return true;
        }
        else{
            throw new IllegalArgumentException("Can't place animal on" + animal.getPosition());
        }
    }

    public boolean isOccupied(Vector2d position) {
        return natures.get(position) != null;
    }


    public Object objectAt(Vector2d position) {
        return natures.get(position);
    }
}
