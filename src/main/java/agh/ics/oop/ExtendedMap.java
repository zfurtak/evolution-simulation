package agh.ics.oop;


public class ExtendedMap extends AbstractWorldMap {

    public ExtendedMap(int x){
        placePlants(x);
    }


    @Override
    public boolean canMoveTo(Vector2d position) {
        return false;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return false;
    }
}


