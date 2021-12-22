package agh.ics.oop;

import javafx.scene.image.Image;


public class Animal extends AbstractWorldMapElement {
    private MapDirection orient = MapDirection.NORTH;
    private final AbstractWorldMap map;
    private int energy;
    private Genome genome;
    private int childrenNo = 0;
    public int birthday;


    public Animal(AbstractWorldMap map) {
        this.orient = this.orient.startOrient();
        this.map = map;
        this.energy = map.startEnergy;
        this.genome = new Genome();
        this.birthday = 0;
        uploadGenomes();
    }

    public Animal(AbstractWorldMap map, int energyValue, Animal mum, Animal dad, int birthday) {
        this.position = mum.position;
        this.map = map;
        this.energy = energyValue;
        this.genome = new Genome(mum, dad);
        this.birthday = birthday;
        uploadGenomes();
    }

    public void setPosition(Vector2d pos) {
        this.position = pos;
    }

    public boolean isAnimalDead() {
        return this.energy <= 0;
    }

    public void move() {
        int move = genome.randomGene();
        Vector2d newPosition;
        boolean flag = this.map instanceof ExtendedMap;
        switch (move) {
            case 0 -> {
                newPosition = this.position.add(this.orient.toUnitVector());
                if (flag && !this.map.canMoveTo(newPosition)) {
                    newPosition = teleport(newPosition);

                }
                if(this.map.canMoveTo(newPosition)){
                    notify(this.position, newPosition, this);
                    this.position = newPosition;
                }
            }
            case 1 -> turn(1);
            case 2 -> turn(2);
            case 3 -> turn(3);
            case 4 -> {
                newPosition = this.position.subtract(this.orient.toUnitVector());
                if (flag && !this.map.canMoveTo(newPosition)) {
                    newPosition = teleport(newPosition);
                }
                if(this.map.canMoveTo(newPosition)){
                    notify(this.position, newPosition, this);
                    this.position = newPosition;
                }
            }
            case 5 -> turn(5);
            case 6 -> turn(6);
            case 7 -> turn(7);
        }
    }

    public Vector2d teleport(Vector2d newPosition){
        if(newPosition.x > map.rightUpCorner.x) {
            newPosition = newPosition.subtract(new Vector2d(map.width, 0));
        } else if(newPosition.x < 0){
            newPosition = newPosition.add(new Vector2d(map.width, 0));
        }
        if(newPosition.y > map.rightUpCorner.y){
            newPosition = newPosition.subtract(new Vector2d(0, map.height));
        } else if(newPosition.y < 0){
            newPosition = newPosition.add(new Vector2d(0, map.height));
        }
        return newPosition;
    }

    public void yummy(int x) {
        this.energy += (map.plantEnergy / x);
    }

    public void reproduce(Animal partner) {
        this.energy -= this.energy * 0.25;
        partner.energy -= partner.energy * 0.25;
        this.childrenNo ++;
        partner.childrenNo ++;
    }

    public void exercise() {
        this.energy -= map.moveEnergy;
    }

    public void uploadGenomes(){
        if(map.genomes.get(this.genome) == null){
            map.genomes.put(this.genome, 1);
        }else{
            int value = map.genomes.get(this.genome);
            map.genomes.remove(this.genome);
            map.genomes.put(this.genome, value + 1);
        }
    }

    private void turn(int x) {
        for (int i = 0; i < x; i++) {
            this.orient = this.orient.next();
        }
        notify(this.position, this.position, this);
    }

    public int getEnergy() {
        return this.energy;
    }

    public int getChildrenNo(){
        return this.childrenNo;
    }

    public int getGene(int i) {
        return this.genome.genomeArray[i];
    }

    public Genome getGenome(){
        return this.genome;
    }

    @Override
    public Image getImage() {
        double energy = this.energy / (double) map.startEnergy;
        return map.getImageLoader().animalsImages[Math.min(9, Math.max(0, (int) (energy*10)))];
    }
}
