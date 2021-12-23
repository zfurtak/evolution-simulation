package agh.ics.oop;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GenomeTest {
    AbstractWorldMap map = new NotExtendedMap(10, 10, 0.2, 10, 10, 1);

    //testing if baby's genome is generated properly

    @Test
    public void genomeTest1(){
        Animal mum = new Animal(map);
        Animal dad = new Animal(map);
        mum.setEnergy(5);
        Animal baby = new Animal(map, 10, mum, dad, 1);
        int[] babyGenome = baby.getGenome().genomeArray;
        int[] mumGenome = mum.getGenome().genomeArray;
        int[] dadGenome = dad.getGenome().genomeArray;

        assertTrue((Arrays.equals(babyGenome, 0, 21, dadGenome, 0, 21)
                || (Arrays.equals(babyGenome, 10, 31, dadGenome, 10, 31))));

        assertFalse((Arrays.equals(babyGenome, 0, 21, mumGenome, 0, 21)
                && (Arrays.equals(babyGenome, 10, 31, mumGenome, 10, 31))));
    }

    @Test
    public void genomeTest2(){
        Animal mum = new Animal(map);
        Animal dad = new Animal(map);
        dad.setEnergy(8);
        Animal baby = new Animal(map, 10, mum, dad, 1);
        int[] babyGenome = baby.getGenome().genomeArray;
        int[] mumGenome = mum.getGenome().genomeArray;
        int[] dadGenome = dad.getGenome().genomeArray;

        assertTrue((Arrays.equals(babyGenome, 0, 16, mumGenome, 0, 16)
                || (Arrays.equals(babyGenome, 14, 31, mumGenome, 14, 31))));
        assertFalse((Arrays.equals(babyGenome, 0, 16, dadGenome, 0, 16)
                && (Arrays.equals(babyGenome, 14, 31, dadGenome, 14, 31))));

    }

}
