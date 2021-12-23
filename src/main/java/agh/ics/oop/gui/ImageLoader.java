package agh.ics.oop.gui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

//storing image assigned to an animal

public class ImageLoader {
    public Image[] animalsImages = new Image[10];
    public Image plantImage;

    public ImageLoader(){
        for(int i = 0; i < 10; i++){
            try {
                animalsImages[i] = new Image(new FileInputStream("src/main/resources/"+(i+1)+".png"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        try {
            plantImage = new Image(new FileInputStream("src/main/resources/plant.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
