package agh.ics.oop;

import agh.ics.oop.gui.SideBox;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;


public class SimulationEngine implements Runnable{
    private final AbstractWorldMap map;
    private int daysCounter;
    private final SideBox side;
    private int magicCounter = 0;
    List<String[]> dataLines = new ArrayList<>();
    double[] average = new double[]{0, 0, 0, 0, 0, 0};

    public SimulationEngine(AbstractWorldMap map, SideBox sideBox) {
        this.map = map;
        this.side = sideBox;
    }

    //all activities made daily

    @Override
    public void run() {
        while(true){
            try {
                Thread.sleep(200);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            map.removeDeadAnimals(daysCounter);
            this.magicSystem();
            map.moveAnimals();
            map.eatDinner();
            map.makeLove(daysCounter);
            map.placePlants();
            this.drawNewMap();
            this.daysCounter ++;
        }
    }

    // generating map, stats and chart every day

    public void drawNewMap(){
        map.avgEnergy = map.getAnimalLinkedList().stream().mapToInt(Animal :: getEnergy).sum() / Math.max(map.animalsQuantity, 1);
        map.avgLifeTime = ((map.deadAnimalsNo-map.newDeathsNo) * map.avgLifeTime + map.newLifeTimeData) / Math.max(map.deadAnimalsNo, 1);
        map.avgChildrenNo = map.getAnimalLinkedList().stream().mapToInt(Animal :: getChildrenNo).sum() / Math.max(map.animalsQuantity, 1);
        if(map.animalsQuantity != 0)
            map.mostCommonGenome = Collections.max(map.genomes.entrySet(), Comparator.comparingInt(Map.Entry::getValue)).getKey();
        averageStats();
        String[] data = {Integer.toString(daysCounter), Integer.toString(map.animalsQuantity), Integer.toString(map.plantsQuantity),
                Integer.toString(map.avgEnergy), Integer.toString(map.avgLifeTime), Integer.toString(map.avgChildrenNo)};
        dataLines.add(data);
        this.side.topBox.customMap.positionChanged();
        this.side.topBox.stats.updateGenome(map);
        this.side.getChart(this.daysCounter);

    }

    //calculating average statistics

    public void averageStats(){
        double avgAnimalsQuantity = (average[1] * daysCounter + map.animalsQuantity) / (double) (daysCounter + 1);
        double avgPlantsQuantity = (average[2] * this.daysCounter + map.plantsQuantity) / (double)(this.daysCounter + 1);
        double avgAvgEnergy = (average[3] * daysCounter + map.avgEnergy) / (double) (daysCounter + 1);
        double avgAvgLifeTime = (average[4] * daysCounter + map.avgLifeTime) / (double) (daysCounter + 1);
        double avgAvgChildNo = (average[5] * daysCounter + map.avgChildrenNo) / (double) (daysCounter + 1);
        average[0] = daysCounter;
        average[1] = avgAnimalsQuantity;
        average[2] = avgPlantsQuantity;
        average[3] = avgAvgEnergy;
        average[4] = avgAvgLifeTime;
        average[5] = avgAvgChildNo;
    }

    //turning on magic mode (if checked)

    public void magicSystem(){
        if(map.isMagic && map.animalsQuantity == 5 && magicCounter < 3){
            map.placeMagicAnimals(daysCounter);
            magicCounter ++;
            this.side.topBox.stats.updateMagicCounter(magicCounter);
        }
    }

    //saving to file

    public String convertToCSV(String[] data) {
        return String.join(",", data);
    }

    public void makeCSVFile() throws IOException {
        File csvFile = new File("src\\main\\resources\\test.csv");
        try (PrintWriter pw = new PrintWriter(csvFile)) {
            pw.println(convertToCSV(new String[]{"days", "animals", "plants", "energy", "lifetime", "children"}));
            dataLines.stream()
                    .map(this::convertToCSV)
                    .forEach(pw::println);

            final int[] intAverage = new int[average.length];
            for (int i = 0; i < intAverage.length; ++i)
                intAverage[i] = (int) average[i];

            String avg = Arrays.toString(intAverage);
            String[] arrayAvg = avg.substring(1, avg.length() - 1).split(", ");
            pw.println(convertToCSV(arrayAvg));
        }
    }
}
