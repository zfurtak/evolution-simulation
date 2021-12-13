package agh.ics.oop;

public class Animal extends AbstractWorldMapElement{
    private MapDirection orient = this.orient.startOrient();
    private AbstractWorldMap map;
    private int energy;
    private Genome genome;



    public Animal(AbstractWorldMap map) {
        this.position = new Vector2d((int) (Math.random() * map.width), (int) (Math.random() * map.height));
        //this.position = new Vectord2d((int) (Math.random() * map.width), (int) (Math.random() * map.height));
        this.map = map;
        this.energy = startEnergy;
        this.genome = new Genome();
    }

    public Animal(AbstractWorldMap map, int energyValue, Animal mum, Animal dad) {
        this.position = new Vector2d((int) (Math.random() * map.width), (int) (Math.random() * map.height));
        //this.position = new Vectord2d((int) (Math.random() * map.width), (int) (Math.random() * map.height));
        this.map = map;
        this.energy = energyValue;
        this.genome = new Genome(mum, dad);
    }

    public boolean isAnimalDead(){
         return this.energy <= 0;
     }

    public void move(){
        int move = genome.randomGene();
        switch (move){
            case 0 -> {
                Vector2d newPosition = this.position.add(this.orient.toUnitVector());
                if (this.map.canMoveTo(newPosition)){
                    notify(this.position, newPosition);
                    this.position = newPosition;
                }
            }
            case 1 -> turn(1);
            case 2 -> turn(2);
            case 3 -> turn(3);
            case 4 -> {
                Vector2d newPosition = this.position.subtract(this.orient.toUnitVector());
                if (this.map.canMoveTo(newPosition)){
                    notify(this.position, newPosition);
                    this.position = newPosition;
                }
            }
            case 5 -> turn(5);
            case 6 -> turn(6);
            case 7 -> turn(7);
        }
    }

    public void yummy(int x){
        this.energy += (int)(plantEnergy/x);
    }

    public void reproduce(Animal partner){
        this.energy -= this.energy * 0.25;
        partner.energy -= partner.energy * 0.25;
    }

    public void exercise(){
        this.energy -= moveEnergy;
    }

    private void turn(int x) {
        for(int i = 0; i < x; i++) {
            this.orient = this.orient.next();
        }
        notify(this.position, this.position);
    }


    public int getEnergy(){
        return this.energy;
    }

    public int getGene(int i){
        return this.genome.genomeArray[i];
    }

    public MapDirection getOrient() {
        return this.orient;
    }


    public String toString() {
        return "Z";
    }

    @Override
    public String getPath() {
        return "src/main/resources/up_doggo.png";
        }
    }
