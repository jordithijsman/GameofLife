package sample.model;

import com.sun.prism.paint.RadialGradient;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class World {

    private int breedte;
    private int hoogte;
    private Map<Integer, Vakje> yMap = new HashMap<>();
    private Map<Integer, Map<Integer, Vakje>> puntenMap = new HashMap<>();

    public World(File file) throws FileNotFoundException {
    }

    public World(int width, int height) {
        this.breedte = width;
        this.hoogte = height;
        for (int i = 0; i < hoogte; i++) {
            yMap.put(i, new Vakje());
        }
        for (int i = 0; i < breedte; i++) {
            puntenMap.put(i, yMap);
        }
    }

    public void toggleCell(int x, int y) {
        if(puntenMap.get(x).get(y).getAlive()){
            puntenMap.get(x).get(y).setAlive(false);
        }else{
            puntenMap.get(x).get(y).setAlive(true);
        }
    }

    public void saveToFile(final File file) throws IOException {
    }

    public void nextGeneration() {
    }

    public int getWidth() {
        return this.breedte;
    }

    public int getHeight() {
        return this.hoogte;
    }

    public boolean isAliveAt(int x, int y) {
        return puntenMap.get(x).get(y).getAlive();
    }

    public void randomCells() { // vult hele lijn??
        Random rd = new Random();
        for(Map<Integer, Vakje> yCo : puntenMap.values()){
            for(Vakje vak: yCo.values()){
                vak.setAlive(rd.nextBoolean());
            }
        }
    }

}
