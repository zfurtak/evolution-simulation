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
    protected int jungleRatio;
    private int minReproduceEnergy;

    //just printing

    public String toString() {
        MapVisualizer mapVisualizer = new MapVisualizer(this);
        return mapVisualizer.draw(findingLowerCorner(), findingUpperCorner());
    }

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
    public void useAnimalEnergy(){
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
        int childEnergy = (int) ((animalsList.get(0).getEnergy() + animalsList.get(1).getEnergy()) / 4);
        Animal baby = new Animal(this, childEnergy, animalsList.get(0), animalsList.get(1));
        animalsList.get(0).reproduce(animalsList.get(1));
    }


    static class EnergyComp implements Comparator<Animal>{
        @Override
        public int compare(Animal a1, Animal a2) {
            return a1.getEnergy() - a2.getEnergy();
        }
    }

    // putting animals and plants on the map

    public boolean place(Animal animal) {
        if (canMoveTo(animal.getPosition())) {
            this.animals.get(animal.getPosition()).add(animal);
            animal.addObserver(this);
            return true;
        } else {
            throw new IllegalArgumentException("Can't place animal on" + animal.getPosition());
        }
    }


    public void placePlants(int amount) {
        int x, y;
        Vector2d plantPosition;

        // plant growing in the jungle

        for (int i = 0; i < (int) (amount / 2); i++) {
            x = (int) (Math.random() * (this.width * jungleRatio));
            y = (int) (Math.random() * (this.height * jungleRatio));
            plantPosition = new Vector2d(x, y);
            while(isOccupied(plantPosition)) {
                x = (int) (Math.random() * Math.sqrt(amount * 10));
                y = (int) (Math.random() * Math.sqrt(amount * 10));
                plantPosition = new Vector2d(x, y);
            }
            Plant junglePlant = new Plant(plantPosition);
            plants.put(plantPosition, junglePlant);
        }
        // plant growing outside the jungle, on steppe

        for (int i = 0; i < (int) (amount / 2); i++) {
            x = (int) (Math.random() * this.width);
            y = (int) (Math.random() * this.height);
            plantPosition = new Vector2d(x, y);
            while (isOccupied(plantPosition) || (x <= (this.width * jungleRatio) && y <= (this.height * jungleRatio))) {
                x = (int) (Math.random() * Math.sqrt(amount * 10));
                y = (int) (Math.random() * Math.sqrt(amount * 10));
                plantPosition = new Vector2d(x, y);
            }
            Plant steppePlant = new Plant(plantPosition);
            plants.put(plantPosition, steppePlant);
        }
    }


    // all the stuff with elements on the map

    public LinkedHashMap<Vector2d, LinkedList<Animal>> getAnimals() {
        return this.animals;
    }

    public boolean isOccupied(Vector2d position) {
        return animals.get(position) != null && plants.get(position) != null;
    }

    public boolean isPlantThere(Vector2d position){
        return plants.get(position) != null;
    }

    public Object objectAt(Vector2d position) {
        if(animals.get(position) != null)
            return animals.get(position);
        return plants.get(position);
    }

    public boolean canMoveTo(Vector2d position) {
        return !(objectAt(position) instanceof Animal);
    }

    // just giving back corners of the map

    public Vector2d findingUpperCorner() {
        return rightUpCorner;
    }

    public Vector2d findingLowerCorner(){
        return new Vector2d(0, 0);
    }
}