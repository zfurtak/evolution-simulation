package agh.ics.oop;

import java.util.Arrays;

public class Genome {
    public int[] genomeArray;
    private final int size = 32;
    private final int genesQuantity = 7;

    public Genome(){
        this.genomeArray = new int[size];
        makeGenome();
    }

    public void makeGenome(){
        for(int i = 0; i < size; i++){
            this.genomeArray[i] = (int) (Math.random() * (genesQuantity));
        }
        Arrays.sort(this.genomeArray);
    }

    public int getGene(){
        int temp = (int) (Math.random() * (size));
        return this.genomeArray[temp];
    }

}
