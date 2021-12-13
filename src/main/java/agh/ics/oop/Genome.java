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

    public void getGenome(Animal mum, Animal dad){
        int mumEnergy = mum.getEnergy();
        int dadEnergy = dad.getEnergy();
        int parentsEnergy = mumEnergy + dadEnergy;
        Random r = new Random();
        int side = r.nextInt(2); // 0 -> left genome side /\ 1 -> right side
        if (mumEnergy >= dadEnergy){
            fillProperSide(mum, dad, mumEnergy, parentsEnergy, side);
        }else{
            fillProperSide(dad, mum, dadEnergy, parentsEnergy, side);
        }
    }

    private void fillProperSide(Animal betterParent, Animal worseParent, int betterEnergy, int wholeEnergy, int option){
        switch(option){
            case 0 -> { // filling right side by betterParent
                int bound = (int) size*(betterEnergy/wholeEnergy);
                for(int i = 0; i < bound; i++){
                    this.genomeArray[i] = betterParent.getGene(i);
                }
                for(int i = bound; i < size; i++){
                    this.genomeArray[i] = worseParent.getGene(i);
                }
            }
            case 1 -> { // filling left side by betterParent
                int bound = (int) size * (1 - (betterEnergy/wholeEnergy));
                for(int i = 0; i < bound; i++){
                    this.genomeArray[i] = worseParent.getGene(i);
                }
                for(int i = bound; i < size; i++){
                    this.genomeArray[i] = betterParent.getGene(i);
                }
            }
        }
    }


    public void randomGenome(){
        for(int i = 0; i < size; i++){
            this.genomeArray[i] = (int) (Math.random() * (genesQuantity));
        }
        Arrays.sort(this.genomeArray);
    }
    
    
    // picking random gene from genome with appropriate probability

    public int randomGene(){
        int temp = (int) (Math.random() * (size));
        return this.genomeArray[temp];
    }

}
