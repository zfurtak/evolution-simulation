package agh.ics.oop;


public class TreeMap extends AbstractWorldMap {
    public Vector2d rightUpCorner;
    public TreeMap(int x){
        placeGrass(x);
    }

    public void placeGrass(int amount) {
        int x, y;
        Vector2d grassPosition;
        for (int i = 0; i < amount; i++) {
            x = (int) (Math.random() * Math.sqrt(amount * 10));
            y = (int) (Math.random() * Math.sqrt(amount * 10));
            grassPosition = new Vector2d(x, y);
            while(isOccupied(grassPosition)) {
                x = (int) (Math.random() * Math.sqrt(amount * 10));
                y = (int) (Math.random() * Math.sqrt(amount * 10));
                grassPosition = new Vector2d(x, y);
            }
            Tree kempka = new Tree(grassPosition);
            natures.put(grassPosition, kempka);

        }
    }

    @Override
    public void positionChanged(Vector2d oldPos, Vector2d newPos){
        AbstractWorldMapElement newObject = natures.get(newPos);
        super.positionChanged(oldPos, newPos);
        if (newObject instanceof Tree)
            this.placeGrass(1);
    }

    @Override
    public Vector2d findingUpperCorner() {
        return rightUpCorner;
    }

    public Vector2d findingLowerCorner(){
        return new Vector2d(0, 0);
    }
}


