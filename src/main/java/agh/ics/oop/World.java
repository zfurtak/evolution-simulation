package agh.ics.oop;
import agh.ics.oop.gui.App;
import javafx.application.Application;

import static java.lang.System.*;


public class World {
    public static void main(String[] args){
        Application.launch(App.class, args);

    }

    public static MoveDirection[] toEnum(String [] args) {
        int size = args.length;
        MoveDirection[] kierunki = new MoveDirection[size];
        int i = 0;
        for (String arg : args) {
            switch (arg) {
                case "f" -> kierunki[i] = MoveDirection.FORWARD;
                case "r" -> kierunki[i] = MoveDirection.RIGHT;
                case "l" -> kierunki[i] = MoveDirection.LEFT;
                case "b" -> kierunki[i] = MoveDirection.BACKWARD;
            }
            i++;
        }
            return kierunki;
    }
        public static void run (MoveDirection[] kierunki){
            for (MoveDirection arg : kierunki) {
                switch (arg) {
                    case FORWARD -> out.println("Do przodu");
                    case RIGHT -> out.println("Na prawo");
                    case LEFT -> out.println("Na lewo");
                    case BACKWARD -> out.println("Do tyłu");
                }
            }

        }

    }