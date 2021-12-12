package agh.ics.oop;

public class Animal extends AbstractWorldMapElement{
    private MapDirection orient = MapDirection.NORTH;
    private final AbstractWorldMap map;



    public Animal(AbstractWorldMap map, Vector2d initialPosition) {
        this.position = initialPosition;
        this.map = map;
    }


    public MapDirection getOrient() {
        return this.orient;
    }

    public void move(MoveDirection direction){
        switch (direction){
            case LEFT -> this.orient = this.orient.previous();
            case RIGHT -> this.orient = this.orient.next();
            case FORWARD -> {
                Vector2d newPosition = this.position.add(this.orient.toUnitVector());
                if (this.map.canMoveTo(newPosition)){
                    notify(this.position, newPosition);
                    this.position = newPosition;
                }
            }
            case BACKWARD -> {
                Vector2d newPosition = this.position.subtract(this.orient.toUnitVector());
                if (this.map.canMoveTo(newPosition)){
                    notify(this.position, newPosition);
                    this.position = newPosition;
                }
            }
        }
    }

    @Override
    public String getPath() {
        return switch (((Animal) this).getOrient()){
            case NORTH -> "src/main/resources/up_doggo.png";
            case SOUTH -> "src/main/resources/down_doggo.png";
            case WEST -> "src/main/resources/left_doggo.png";
            case EAST -> "src/main/resources/right_doggo.png" ;
        };
    }
}
