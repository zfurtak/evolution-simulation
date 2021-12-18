package agh.ics.oop;

import java.util.ArrayList;

public class SimulationEngine implements Runnable{
    private MoveDirection[] moves;
    private final ArrayList<Animal> animals;
    private final AbstractWorldMap map;
    private final int moveDelay;

    public SimulationEngine(AbstractWorldMap map, int animalQuantity, int moveDelay){
        this.animals = new ArrayList<>();
        this.map = map;
        this.moveDelay = moveDelay;
        for (int i = 0; i < animalQuantity; i++){
            Animal animal = new Animal(map);
            this.animals.add(animal);
            map.placeAnimal(animal);
        }
    }

    public void getMoves(MoveDirection[] newMoves){
        this.moves = newMoves;
    }

    @Override
    public void run() {
        int movesLength = this.moves.length;
        int animalsSize = this.animals.size();
        for (int i = 0; i < movesLength; i++){
            int animalID = i % animalsSize;
            Animal animal = this.animals.get(animalID);
            animal.move();
            try {
                Thread.sleep(this.moveDelay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
