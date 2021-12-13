package agh.ics.oop;
import agh.ics.oop.gui.App;
import javafx.application.Application;

import static java.lang.System.*;


public class World {
    public static void main(String[] args){
        AbstractWorldMap map = new NotExtendedMap(10);
        Animal jim = new Animal(map);
        Animal pam = new Animal(map);
        out.println(map.toString());

    }
    }