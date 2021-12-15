package agh.ics.oop;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Comparator;



public abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver {
    protected LinkedHashMap<Vector2d, LinkedList<Animal>> animals = new LinkedHashMap<>();
    protected LinkedHashMap<Vector2d, Plant> plants = new LinkedHashMap<>();
    protected Vector2d rightUpCorner;
    protected int height;
    protected int width;
    protected double jungleRatio;
    protected int startEnergy;
    protected int minReproduceEnergy; //// !!!!!!!!!!!!!!!!!!!!!!!!!!!
    protected int plantEnergy;
    protected int moveEnergy;
    protected Vector2d jungleDownCorner;
    protected Vector2d jungleUpCorner;


    // when animals are moving

    public void positionChanged(Vector2d oldPos, Vector2d newPos) {
        LinkedList<Animal> animalsOldPlace = this.animals.get(oldPos);
        LinkedList<Animal> animalsNewPlace = this.animals.get(newPos);
        for(Animal animal : animalsOldPlace){
            animalsOldPlace.remove(animal);
            animalsNewPlace.add(animal);
        }
    }

    public void moveAnimals() {
        for (LinkedList<Animal> list : this.animals.values()) {
            for(Animal animal : list){
                animal.move();
            }
        }
    }

    //changing animals' energy after a day
    public void animalsEnergyAfterDay(){
        for (LinkedList<Animal> list : this.animals.values()) {
            for(Animal animal : list){
                animal.exercise();
            }
        }
    }

    // eating plants and dividing it to animals with same energy

    public void eatingPlant(Vector2d position){
        LinkedList<Animal> animalsList = animals.get(position);
        animalsList.sort(new EnergyComp());
        int mostEnergy = animalsList.get(0).getEnergy();
        int i = 0;
        int counter = 0;
        while(animalsList.get(i).getEnergy() == mostEnergy){
            counter ++;
            i ++;
        }
        for(int j = 0; j < counter; j++){
            animalsList.get(j).yummy(counter);
        }
    }


    //reproducing

    public void makingLittleAnimal(Vector2d position){
        LinkedList<Animal> animalsList = animals.get(position);
        animalsList.sort(new EnergyComp());
        if (animalsList.get(1).getEnergy() >= minReproduceEnergy) {
            int childEnergy = (int) ((animalsList.get(0).getEnergy() + animalsList.get(1).getEnergy()) / 4);
            Animal baby = new Animal(this, childEnergy, animalsList.get(0), animalsList.get(1));
            baby.position = position;
            animalsList.get(0).reproduce(animalsList.get(1));
            placeBaby(baby);
        }
    }


    public static class EnergyComp implements Comparator<Animal>{
        @Override
        public int compare(Animal a1, Animal a2) {
            return a1.getEnergy() - a2.getEnergy();
        }
    }

    // putting start animals (animals have to be on different spots)
    // used also when copies are made in magic version of evolution

    public boolean placeAnimal(Animal animal) {
        Vector2d location = new Vector2d((int) (Math.random() * this.width), (int) (Math.random() * this.height));
        int counter = 0;
        while(isOccupied(location)) {
            counter ++;
            location = new Vector2d((int) (Math.random() * this.width), (int) (Math.random() * this.height));
            if(counter == (width*height/2)){
                throw new IllegalArgumentException("Can't place animal on" + location);
            }
        }
        animal.setPosition(location);
        if(this.animals.get(location) == null){
            this.animals.put(location, new LinkedList<Animal>());
            this.animals.get(location).add(animal);
        }else{
            this.animals.get(location).add(animal);
        }
        animal.addObserver(this);
        return true;
    }

    //putting baby animal on the map

    public void placeBaby(Animal animal){
        Vector2d location = animal.getPosition();
        this.animals.get(location).add(animal);
        animal.addObserver(this);
    }

    // placing plants on the map

    public void placePlants() {
        int x, y;
        Vector2d plantPosition;

        // plant growing in the jungle


        x = (int) (Math.random() * (this.width * jungleRatio));
        y = (int) (Math.random() * (this.height * jungleRatio));
        plantPosition = new Vector2d(x, y);
        int counter = 0;
        while(isOccupied(plantPosition)) {
            counter ++;
            x = (int) (Math.random() * Math.sqrt(10));
            y = (int) (Math.random() * Math.sqrt(10));  ///JAK DZIALA RANDOM
            plantPosition = new Vector2d(x, y);
            if(counter == 11){
                plantPosition = jungleResearch();
                break;
            }
        }
        if(plantPosition != null){
            Plant junglePlant = new Plant(plantPosition);
            plants.put(plantPosition, junglePlant);
        }

        // plant growing outside the jungle, on steppe

        x = (int) (Math.random() * this.width);
        y = (int) (Math.random() * this.height);
        plantPosition = new Vector2d(x, y);

        while (isOccupied(plantPosition) || (x <= (this.width * jungleRatio) && y <= (this.height * jungleRatio))) {
            x = (int) (Math.random() * Math.sqrt(10));
            y = (int) (Math.random() * Math.sqrt(10));
            plantPosition = new Vector2d(x, y);
        }
        Plant steppePlant = new Plant(plantPosition);
        plants.put(plantPosition, steppePlant);

    }


    public Vector2d jungleResearch(){
        for(int i = jungleDownCorner.x; i < jungleUpCorner.x; i++){
            for(int j = jungleDownCorner.y; j < jungleUpCorner.y; j++){
                if(!isOccupied(new Vector2d(i, j))){
                    return new Vector2d(i, j);
                }
            }
        }
        return null;
    }

    // all the stuff with elements on the map

    public LinkedHashMap<Vector2d, LinkedList<Animal>> getAnimals() {
        return this.animals;
    }

    public Plant getPlant(Vector2d pos){
        return plants.get(pos);
    }

    public boolean isCrowded(Vector2d position) {
        return animals.get(position).size() > 1;
    }

    abstract public boolean canMoveTo(Vector2d pos);

    public boolean isAnimalThere(Vector2d position){
        return animals.get(position) != null;
    }

    public boolean isOccupied(Vector2d pos){
        return (isAnimalThere(pos) || isPlantThere(pos));
    }

    public boolean isPlantThere(Vector2d position){
        return plants.get(position) != null;
    }

    public Object objectAt(Vector2d position) {
        if(animals.get(position) != null)
            return animals.get(position);
        return plants.get(position);
    }

    // just giving back corners of the map

    public Vector2d findingUpperCorner() {
        return rightUpCorner;
    }

}