package agh.ics.oop;

import agh.ics.oop.gui.ImageLoader;
import javafx.scene.image.ImageView;

import java.util.*;

import static java.lang.System.out;


public abstract class AbstractWorldMap implements IPositionChangeObserver {
    protected LinkedHashMap<Vector2d, LinkedList<Animal>> animals = new LinkedHashMap<>();
    protected LinkedHashMap<Vector2d, Plant> plants = new LinkedHashMap<>();
    protected LinkedList<Animal> animalLinkedList = new LinkedList<>();
    public LinkedHashMap<Genome, Integer> genomes = new LinkedHashMap<>();
    protected Vector2d rightUpCorner;
    protected int height;
    protected int width;
    protected double jungleRatio;
    protected int startEnergy;
    protected int minReproduceEnergy;
    protected int plantEnergy;
    protected int moveEnergy;
    protected boolean isMagic;
    protected Vector2d jungleDownCorner;
    protected Vector2d jungleUpCorner;
    protected ImageLoader imageLoader = new ImageLoader();
    public int animalsQuantity;
    public int plantsQuantity;
    public int avgEnergy;
    public int avgChildrenNo;
    public int newDeathsNo;
    public int deadAnimalsNo = 0;
    public int avgLifeTime = 1;
    public int newLifeTimeData;
    public Genome mostCommonGenome;


    // placing plants on the map

    public void placePlants() {
        int x, y;
        Vector2d plantPosition;

        // plant growing in the jungle

        x = (int) (Math.random() * (jungleUpCorner.x-jungleDownCorner.x+1)+jungleDownCorner.x);
        y = (int) (Math.random() * (jungleUpCorner.y-jungleDownCorner.y+1)+jungleDownCorner.y);
        plantPosition = new Vector2d(x, y);
        int counter = 0;
        while(isPlantThere(plantPosition) || isAnimalThere(plantPosition)) {
            counter ++;
            x = (int) (Math.random() * (jungleUpCorner.x-jungleDownCorner.x)+jungleDownCorner.x);
            y = (int) (Math.random() * (jungleUpCorner.y-jungleDownCorner.y)+jungleDownCorner.y);
            plantPosition = new Vector2d(x, y);
            if(counter == 11){
                plantPosition = jungleResearch();
                break;
            }
        }
        if(plantPosition != null){
            Plant junglePlant = new Plant(plantPosition, this);
            plants.put(plantPosition, junglePlant);
            plantsQuantity ++;
        }

        // plant growing outside the jungle, on steppe

        x = (int) (Math.random() * this.width);
        y = (int) (Math.random() * this.height);
        plantPosition = new Vector2d(x, y);
        counter = 0;
        while (isPlantThere(plantPosition) || isAnimalThere(plantPosition) || (jungleDownCorner.x <= x && x <= jungleUpCorner.x
                && jungleDownCorner.y <= y && y <= jungleUpCorner.y)) {
            counter ++;
            x = (int) (Math.random() * Math.sqrt(10));
            y = (int) (Math.random() * Math.sqrt(10));
            plantPosition = new Vector2d(x, y);
            if(counter == 10){
                break;
            }
        }
        Plant steppePlant = new Plant(plantPosition, this);
        plants.put(plantPosition, steppePlant);
        plantsQuantity++;
    }

// searching for a place to grow in the jungle

    public Vector2d jungleResearch(){
        for(int i = jungleDownCorner.x; i <= jungleUpCorner.x; i++){
            for(int j = jungleDownCorner.y; j <= jungleUpCorner.y; j++){
                if(!isPlantThere(new Vector2d(i, j)) && !isAnimalThere(new Vector2d(i, j))){
                    return new Vector2d(i, j);
                }
            }
        }
        return null;
    }

    // when animals are moving

    public void positionChanged(Vector2d oldPos, Vector2d newPos, Animal animal) {
        LinkedList<Animal> animalsOldPlace = this.animals.get(oldPos);
        this.animals.computeIfAbsent(newPos, k -> new LinkedList<>());
        LinkedList<Animal> animalsNewPlace = this.animals.get(newPos);
        animalsOldPlace.remove(animal);
        animalsNewPlace.add(animal);

    }

    public void moveAnimals() {
        for (Animal animal : animalLinkedList){
                animal.move(10);
                animal.exercise();
        }
    }

    // eating plants and dividing it to animals with same energy

    public void eatDinner() {
        LinkedList<Vector2d> eatenPlants= new LinkedList<>();
        for (Vector2d position : plants.keySet()) {
            if (this.isAnimalThere(position)){
                eatPlant(position);
                eatenPlants.add(position);
            }
        }
        for(Vector2d position : eatenPlants){
            plants.remove(position);
            plantsQuantity --;
        }
    }

    public void eatPlant(Vector2d position){
        LinkedList<Animal> animalsList = animals.get(position);
        animalsList.sort(new EnergyComp());
        int mostEnergy = animalsList.get(0).getEnergy();
        int counter = 0;
        for (Animal animal : animalsList) {
            if (animal.getEnergy() == mostEnergy) {
                counter++;
            }
        }
        for(int j = 0; j < counter; j++){
            animalsList.get(j).yummy(counter);
        }
    }

    //reproducing

    public void makeLove(int birthday){
        Vector2d currentPosition;
        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                currentPosition = new Vector2d(i, j);
                if(isCrowded(currentPosition)){
                    makeLittleAnimal(currentPosition, birthday);
                }
            }
        }
    }

    public void makeLittleAnimal(Vector2d position, int birthday){
        LinkedList<Animal> animalsList = animals.get(position);
        animalsList.sort(new EnergyComp());
        if (animalsList.get(1).getEnergy() >= minReproduceEnergy) {
            int childEnergy = ((animalsList.get(0).getEnergy() + animalsList.get(1).getEnergy()) / 4);
            Animal baby = new Animal(this, childEnergy, animalsList.get(0), animalsList.get(1), birthday);
            baby.position = position;
            animalsList.get(0).reproduce(animalsList.get(1));
            placeBaby(baby);
            this.animalsQuantity ++;
        }
    }

    public void placeBaby(Animal animal){
        Vector2d location = animal.getPosition();
        this.animals.get(location).add(animal);
        animal.addObserver(this);
        animalLinkedList.add(animal);
    }

    //removing dead animals

    public void removeDeadAnimals(int days){
        ArrayList<Animal> deadAnimals = new ArrayList<>();
        newDeathsNo = 0;
        newLifeTimeData = 0;
        for (Animal animal : animalLinkedList){
            if(animal.isAnimalDead()){
                animal.removeObserver(this);
                animals.get(animal.getPosition()).remove(animal);
                deadAnimals.add(animal);
                uploadGenomes(animal);
                this.deadAnimalsNo ++;
                this.newDeathsNo ++;
                newLifeTimeData += days - animal.birthday;
            }
        }
        for(Animal animal: deadAnimals){
            animalLinkedList.remove(animal);
            this.animalsQuantity --;
        }
    }

    //comparator based on animals' energy

    public static class EnergyComp implements Comparator<Animal>{
        @Override
        public int compare(Animal a1, Animal a2) {
            return a1.getEnergy() - a2.getEnergy();
        }
    }

    // putting start animals (animals have to be on different spots)

    public void placeAnimals(int animalsQuantity){
        Animal animal;
        for(int i = 0; i < animalsQuantity; i++){
            animal = new Animal(this);
            place(animal);
            this.animalsQuantity ++;
        }
    }

    //placing animals during magic mode

    public void placeMagicAnimals(int birthday){
        Animal animal;
        LinkedList<Animal> patterns = new LinkedList<Animal>(animalLinkedList);
        for(Animal animalParent : patterns){
            animal = new Animal(this, birthday, animalParent);
            place(animal);
            this.animalsQuantity ++;
        }
    }

    // putting one animal in the map
    // used also when copies are made in magic version of evolution

    public void place(Animal animal) {
        Vector2d location = new Vector2d((int) (Math.random() * this.width), (int) (Math.random() * this.height));
        int counter = 0;
        while(isPlantThere(location) || isAnimalThere(location)) {
            counter ++;
            location = new Vector2d((int) (Math.random() * this.width), (int) (Math.random() * this.height));
            if(counter == (width * height / 2)){
                throw new IllegalArgumentException("Can't place animal on" + location);
            }
        }
        animal.setPosition(location);
        this.animals.computeIfAbsent(location, k -> new LinkedList<Animal>());
        this.animals.get(location).add(animal);
        animalLinkedList.add(animal);
        animal.addObserver(this);
    }

    //uploading genome when animals are being removed

    public void uploadGenomes(Animal animal){
        if(this.genomes.get(animal.getGenome()) == 1){
            this.genomes.remove(animal.getGenome());
        }else if (this.genomes.get(animal.getGenome()) > 1){
            int value = this.genomes.get(animal.getGenome());
            this.genomes.remove(animal.getGenome());
            this.genomes.put(animal.getGenome(), value - 1);
        }
    }

    // all the stuff with elements on the map; getters, setters, checks

    public LinkedHashMap<Vector2d, LinkedList<Animal>> getAnimals() {
        return this.animals;
    }

    public Plant getPlant(Vector2d pos){
        return plants.get(pos);
    }

    public boolean isCrowded(Vector2d position) {
        return animals.get(position) != null && animals.get(position).size() > 1;
    }

    public boolean canMoveTo(Vector2d pos) {
        return (pos.precedes(rightUpCorner) && pos.follows(new Vector2d(0, 0)));
    }

    public boolean isAnimalThere(Vector2d position){
        return animals.get(position) != null && animals.get(position).size() > 0 ;
    }

    public boolean isPlantThere(Vector2d position){
        return plants.get(position) != null;
    }

    public Object objectAt(Vector2d position) {
        if(animals.get(position) != null)
            return animals.get(position);
        return plants.get(position);
    }

    public void setMagic(boolean x){
        this.isMagic = x;
    }

    public int getHeight(){
        return this.height;
    }

    public int getWidth(){
        return this.width;
    }

    public ImageLoader getImageLoader(){
        return this.imageLoader;
    }

    public LinkedList<Animal> getAnimalLinkedList() {
        return animalLinkedList;
    }

    public Vector2d findingUpperCorner() {
        return rightUpCorner;
    }

}