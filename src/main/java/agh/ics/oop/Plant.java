package agh.ics.oop;

import javafx.scene.image.Image;

public class Plant extends AbstractWorldMapElement{
    AbstractWorldMap map;

    public Plant(Vector2d pos, AbstractWorldMap mapDude){
        this.position = pos;
        this.map = mapDude;
    }

    @Override
    public Image getImage() {
        return map.getImageLoader().plantImage;
    }

}
