package agh.ics.oop;

public class RectangularMap extends AbstractWorldMap{
    private final Vector2d rightUpCorner;
    private final Vector2d leftDownCorner = new Vector2d(0,0);

    public RectangularMap(int width, int height){
        this.rightUpCorner = new Vector2d(width-1, height -1);
    }


    @Override
    public boolean canMoveTo(Vector2d position1){
        boolean x = super.canMoveTo(position1);
        return x && position1.precedes(rightUpCorner) && position1.follows(leftDownCorner);
    }

    @Override
    public Vector2d findingUpperCorner() {
        return rightUpCorner;
    }

    @Override
    public Vector2d findingLowerCorner() {
        return leftDownCorner;
    }
}
