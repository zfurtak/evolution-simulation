package agh.ics.oop;

import java.util.*;

public class Genome {
    public int[] genomeArray;
    private final int size = 32;
    private final int genesQuantity = 7;


    public Genome(){
        this.genomeArray = new int[size];
        randomGenome();
    }

    public Genome(Animal mum, Animal dad){
        this.genomeArray = new int[size];
        getGenome(mum, dad);
    }

    //creating genome for a baby animal

    public void getGenome(Animal mum, Animal dad){
        int mumEnergy = mum.getEnergy();
        int dadEnergy = dad.getEnergy();
        int parentsEnergy = mumEnergy + dadEnergy;
        int side = (int) (Math.random() * 2); // 0 -> left genome side /\ 1 -> right side
        System.out.println("side "+side);
        if (mumEnergy >= dadEnergy){
            fillProperSide(mum, dad, mumEnergy, parentsEnergy, side);
        }else{

            System.out.println("tata lepszy");
            fillProperSide(dad, mum, dadEnergy, parentsEnergy, side);
        }
        Arrays.sort(this.genomeArray);
    }

    private void fillProperSide(Animal betterParent, Animal worseParent, int betterEnergy, int wholeEnergy, int option){
        switch(option){
            case 0 -> { // filling right side by betterParent
                int bound = (int) (size*((double)betterEnergy/wholeEnergy));
                for(int i = 0; i < bound; i++){
                    this.genomeArray[i] = betterParent.getGene(i);
                }
                for(int i = bound; i < size; i++){
                    this.genomeArray[i] = worseParent.getGene(i);
                }
            }
            case 1 -> { // filling left side by betterParent
                int bound = (int)(size * (1 - ((double) betterEnergy / wholeEnergy)));
                for(int i = 0; i < bound; i++){
                    this.genomeArray[i] = worseParent.getGene(i);
                }
                for(int i = bound; i < size; i++){
                    this.genomeArray[i] = betterParent.getGene(i);
                }
            }
        }
    }

    // making random genome

    public void randomGenome(){
        for(int i = 0; i < size; i++){
            this.genomeArray[i] = (int) (Math.random() * (genesQuantity+1));
        }
        Arrays.sort(this.genomeArray);
    }

    // picking random gene from genome with appropriate probability

    public int randomGene(){
        int temp = (int) (Math.random() * (size));
        return this.genomeArray[temp];
    }

    public String toString(){
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < size; i++){
            result.append(" ");
            result.append(genomeArray[i]);
        }
        return result.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Genome genome = (Genome) o;
        return Arrays.equals(genomeArray, genome.genomeArray);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(size, genesQuantity);
        result = 31 * result + Arrays.hashCode(genomeArray);
        return result;
    }
}
