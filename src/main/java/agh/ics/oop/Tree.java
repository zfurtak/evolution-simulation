package agh.ics.oop;

public class Tree extends AbstractWorldMapElement{

    public Tree(Vector2d pos){
        this.position = pos;
    }

    @Override
    public String getPath() {
        return "src/main/resources/grass.png";
    }

    /*public String toString(){
       return "*";
    }*/

}
