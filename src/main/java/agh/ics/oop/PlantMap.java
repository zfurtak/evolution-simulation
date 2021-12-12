package agh.ics.oop;


public class PlantMap extends AbstractWorldMap {
    public Vector2d rightUpCorner;


    public PlantMap(int x){
        placePlants(x);
    }

    public void placePlants(int amount) {
        int x, y;
        Vector2d plantPosition;
        // plant growing in the jungle

        for (int i = 0; i < (int) (amount / 2); i++) {
            x = (int) (Math.random() * (this.width * jungleRatio));
            y = (int) (Math.random() * (this.height * jungleRatio));
            plantPosition = new Vector2d(x, y);
            while(isOccupied(plantPosition)) {
                x = (int) (Math.random() * Math.sqrt(amount * 10));
                y = (int) (Math.random() * Math.sqrt(amount * 10));
                plantPosition = new Vector2d(x, y);
            }
            Plant junglePlant = new Plant(plantPosition);
            natures.put(plantPosition, junglePlant);
        }
        // plant growing outside the jungle, on steppe

        for (int i = 0; i < (int) (amount / 2); i++) {
            x = (int) (Math.random() * this.width);
            y = (int) (Math.random() * this.height);
            plantPosition = new Vector2d(x, y);
            while (isOccupied(plantPosition) || (x <= (this.width * jungleRatio) && y <= (this.height * jungleRatio))) {
                x = (int) (Math.random() * Math.sqrt(amount * 10));
                y = (int) (Math.random() * Math.sqrt(amount * 10));
                plantPosition = new Vector2d(x, y);
            }
            Plant steppePlant = new Plant(plantPosition);
            natures.put(plantPosition, steppePlant);
        }
    }

    @Override
    public Vector2d findingUpperCorner() {
        return rightUpCorner;
    }

    public Vector2d findingLowerCorner(){
        return new Vector2d(0, 0);
    }
}


