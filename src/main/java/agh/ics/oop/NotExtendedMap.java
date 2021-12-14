package agh.ics.oop;

public class NotExtendedMap extends AbstractWorldMap{

    public NotExtendedMap(int plantsQuantity, int height, int width, int jungleRatioVal,
                          int startEnergyVal, int plantEnergyVal, int moveEnergyVal){
        placePlants(plantsQuantity);
        this.height = height;
        this.width = width;
        this.rightUpCorner = new Vector2d(width - 1, height - 1);
        this.jungleRatio = jungleRatioVal;
        this.startEnergy = startEnergyVal;
        this.minReproduceEnergy = (int) (startEnergy / 2); //// !!!!!!!!!!!!!!!!!!!!!!!!!!!
        this.plantEnergy = plantEnergyVal;
        this.moveEnergy = moveEnergyVal;
    }

    @Override
    public boolean canMoveTo(Vector2d pos) {
        return pos.precedes(rightUpCorner);
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return false;
    }
}
