package agh.ics.oop;
import agh.ics.oop.gui.App;
import javafx.application.Application;

import static java.lang.System.*;


public class World {
    public static void main(String[] args){
        int plantEnergy = 10;
        int moveEnergy = 1;
        int startEnergy = 10;
        int height = 10;
        int width = 10;
        AbstractWorldMap map = new NotExtendedMap(10, height, width, 20, startEnergy, plantEnergy, moveEnergy);
        Animal jim = new Animal(map);
        Animal pam = new Animal(map);
        map.placeAnimal(jim);
        map.placeAnimal(pam);
        out.println(map.toString());

    }
    }