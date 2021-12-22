package agh.ics.oop;

import agh.ics.oop.gui.App;
import agh.ics.oop.gui.ParametersBox;

import static java.lang.System.out;


public class SimulationEngine implements Runnable{
    private final AbstractWorldMap map;
    private App gui;
    private ParametersBox parametersBox;
    private int daysCounter;



    public SimulationEngine(AbstractWorldMap map, App guiDude){
        this.map = map;
        this.gui = guiDude;
        this.parametersBox = guiDude.getParametersBox();
        this.map.placeAnimals(parametersBox.getAnimalsQuantity());
        if(map instanceof NotExtendedMap)
            this.daysCounter = gui.counter1;
        else
            this.daysCounter = gui.counter2;
    }


    @Override
    public void run() {
        while(true){
            try {
                Thread.sleep(200);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            this.gui.drawNewMap(map);

            map.removeDeadAnimals(daysCounter);
            map.moveAnimals();
            map.eatDinner();
            map.makeLove(daysCounter);
            map.placePlants();
        }
    }
}
