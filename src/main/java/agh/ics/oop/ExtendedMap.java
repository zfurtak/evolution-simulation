package agh.ics.oop;


public class ExtendedMap extends AbstractWorldMap {

    public ExtendedMap(int height, int width, double jungleRatioVal,
                       int startEnergyVal, int plantEnergyVal, int moveEnergyVal) {
        this.height = height;
        this.width = width;
        this.rightUpCorner = new Vector2d(width - 1, height - 1);
        this.jungleRatio = jungleRatioVal;
        this.startEnergy = startEnergyVal;
        this.minReproduceEnergy = startEnergy / 2;
        this.plantEnergy = plantEnergyVal;
        this.moveEnergy = moveEnergyVal;
        this.jungleDownCorner = new Vector2d((int) (width * (1 - jungleRatio) / 2), (int) (height * (1 - jungleRatio) / 2));
        this.jungleUpCorner = new Vector2d((int) (width * (1 + jungleRatio) / 2), (int) (height * (1 + jungleRatio) / 2));
        this.animalsQuantity = 0;
        this.plantsQuantity = 0;
    }
}


