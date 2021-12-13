package agh.ics.oop;

public class Plant extends AbstractWorldMapElement{

    public Plant(Vector2d pos){
        this.position = pos;
    }

    @Override
    public String toString() {
        return "*";
    }

    @Override
    public String getPath() {
        return "src/main/resources/grass.png";
    }


}
