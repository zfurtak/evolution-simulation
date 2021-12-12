package agh.ics.oop;

public class Animal extends AbstractWorldMapElement{
    private MapDirection orient = this.orient.startOrient();
    private AbstractWorldMap map;
    private int energy;
    private Genome genome;


    public Animal(AbstractWorldMap map, Vector2d initialPosition) {
        this.position = initialPosition;
        this.map = map;
        this.energy = 10;
        this.genome = new Genome();
    }


    public void move(){
        int move = genome.getGene();
        switch (move){
            case 0 -> {
                Vector2d newPosition = this.position.add(this.orient.toUnitVector());
                if (this.map.canMoveTo(newPosition)){
                    notify(this.position, newPosition);
                    this.position = newPosition;
                }
            }
            case 1 -> turn(1);
            case 2 -> turn(2);
            case 3 -> turn(3);
            case 4 -> {
                Vector2d newPosition = this.position.subtract(this.orient.toUnitVector());
                if (this.map.canMoveTo(newPosition)){
                    notify(this.position, newPosition);
                    this.position = newPosition;
                }
            }
            case 5 -> turn(5);
            case 6 -> turn(6);
            case 7 -> turn(7);
        }
    }


    private void turn(int x) {
        for(int i = 0; i < x; i++) {
            this.orient = this.orient.next();
        }
        notify(this.position, this.position);
    }


    public MapDirection getOrient() {
        return this.orient;
    }


    @Override
    public String getPath() {
        return switch (((Animal) this).getOrient()){
            case NORTH, NORTH_WEST, SOUTH_WEST, SOUTH_EAST, NORTH_EAST -> "src/main/resources/up_doggo.png";
            case SOUTH -> "src/main/resources/down_doggo.png";
            case WEST -> "src/main/resources/left_doggo.png";
            case EAST -> "src/main/resources/right_doggo.png" ;
        };
        };
    }
