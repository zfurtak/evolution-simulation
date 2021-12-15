package agh.ics.oop;

public class NotExtendedMap extends AbstractWorldMap{

    public NotExtendedMap(int height, int width, double jungleRatioVal,
                          int startEnergyVal, int plantEnergyVal, int moveEnergyVal){
        placePlants();
        this.height = height;
        this.width = width;
        this.rightUpCorner = new Vector2d(width - 1, height - 1);
        this.jungleRatio = jungleRatioVal;
        this.startEnergy = startEnergyVal;
        this.minReproduceEnergy = startEnergy / 2; //// !!!!!!!!!!!!!!!!!!!!!!!!!!!
        this.plantEnergy = plantEnergyVal;
        this.moveEnergy = moveEnergyVal;
        this.jungleDownCorner = new Vector2d((int) (width*(1-jungleRatio)/2), (int) (height*(1-jungleRatio)/2));
        this.jungleUpCorner = new Vector2d((int) (width*(1+jungleRatio)/2), (int) (height*(1+jungleRatio)/2));

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
