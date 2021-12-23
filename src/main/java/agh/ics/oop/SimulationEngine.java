package agh.ics.oop;

import agh.ics.oop.gui.SideBox;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;


public class SimulationEngine implements Runnable{
    private final AbstractWorldMap map;
    private int daysCounter;
    private final SideBox side;
    private int magicCounter = 0;

    public SimulationEngine(AbstractWorldMap map, SideBox sideBox) {
        this.map = map;
        this.side = sideBox;
    }

    //all activities made daily

    @Override
    public void run() {
        while(true){
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            this.drawNewMap();
            map.removeDeadAnimals(daysCounter);
            this.magicSystem();
            map.moveAnimals();
            map.eatDinner();
            map.makeLove(daysCounter);
            map.placePlants();
        }
    }

    // generating map, stats and chart every day

    public void drawNewMap(){
        map.avgEnergy = map.getAnimalLinkedList().stream().mapToInt(Animal :: getEnergy).sum() / map.animalsQuantity;
        map.avgLifeTime = ((map.deadAnimalsNo-map.newDeathsNo) * map.avgLifeTime + map.newLifeTimeData) / Math.max(map.deadAnimalsNo, 1);
        map.avgChildrenNo = map.getAnimalLinkedList().stream().mapToInt(Animal :: getChildrenNo).sum() / map.animalsQuantity;
        map.mostCommonGenome = Collections.max(map.genomes.entrySet(), Comparator.comparingInt(Map.Entry::getValue)).getKey();
        this.side.topBox.customMap.positionChanged();
        this.side.topBox.stats.updateGenome(map);
        this.side.getChart(this.daysCounter);
        this.daysCounter ++;
    }

    //turning on magic mode (if checked)

    public void magicSystem(){
        if(map.isMagic && map.animalsQuantity == 5 && magicCounter < 3){
            map.placeMagicAnimals(daysCounter);
            magicCounter ++;
            this.side.topBox.stats.updateMagicCounter(magicCounter);
        }
    }
}
