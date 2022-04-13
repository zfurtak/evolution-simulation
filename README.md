# Evolution Simulation
### Description
This app was created for Object Oriented Programming course on AGH UST.
*Evolution simulation* was created in `Java` using `JavaFX` UI library.

### General rules
- Each animal has genome containing 32 numbers from 0 to 7 (numbers represent directions, so the whole genome shows probability of animal's movement)
- The animal dies if its energy reaches 0, to survive it has to gain energy by eating plants randomly placed on the map
- Animals can reproduce, if their energy is above 50% of initial energy level
- The resulting child has its genome generated based on genomes and energy levels of the parent
- Reproducing and moving cost the animal energy

### Configurations

At the start of simulation user can choose options which determine evolutionary run.

![image](https://user-images.githubusercontent.com/92310164/163172902-e61ea39c-71a9-45c6-8454-f775c20cce01.png)


### Types

The app provides 2 different types of map: 

- Rolled map - animal is moved onto the other side when runs into edge of the map
- Map with borders - animal is stopped if runs into the edge of the map

### Other functionality

The app can also:

- Graph the simulation statistics in real time
- Show most common genome currently on the map and mark animals with it
- Select a specific animal by clicking on it and show its genome
- Save simulation statistics to a .csv file

### Gameplay demo

![GIF 13 04 2022 13-55-05](https://user-images.githubusercontent.com/92310164/163176059-faf075d4-3d50-4596-a00d-7f3e1130ed2b.gif)


### Requirements

The application was created based on requirements posted here:

https://github.com/apohllo/obiektowe-lab/tree/master/proj1







