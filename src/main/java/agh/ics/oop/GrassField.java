package agh.ics.oop;


public class GrassField extends AbstractWorldMap {

    public GrassField(int x){
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
            boundaryMap.positionChanged(new Vector2d(999, 999), grassPosition);
            kempka.addObserver(boundaryMap);
        }
    }

    @Override
    public void positionChanged(Vector2d oldPos, Vector2d newPos){
        AbstractWorldMapElement newObject = natures.get(newPos);
        super.positionChanged(oldPos, newPos);
        if (newObject instanceof Tree)
            this.placeGrass(1);
    }


    public Vector2d findingUpperCorner() {
        if (boundaryMap.xCoord.isEmpty())
            return new Vector2d(0, 0);
        return new Vector2d(boundaryMap.xCoord.last().x, boundaryMap.yCoord.last().y);
    }

    public Vector2d findingLowerCorner(){
        if(boundaryMap.xCoord.isEmpty())
            return new Vector2d(0, 0);
        return new Vector2d (boundaryMap.xCoord.first().x, boundaryMap.yCoord.first().y);
    }
}


