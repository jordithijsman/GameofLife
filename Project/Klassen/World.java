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
        for (int i = 0; i < breedte; i++) {
            puntenMap.put(i, new HashMap<>());
            for(int k = 0; k < hoogte; k++){
                puntenMap.get(i).put(k, new Vakje());
            }
        }
    }

    public void toggleCell(int x, int y) {
        if(puntenMap.get(x).get(y).getAlive()){
            puntenMap.get(x).get(y).setAlive(false);
        }else{
            puntenMap.get(x).get(y).setAlive(true);
        }
        Buren(x,y);
        System.out.println(puntenMap.get(1).get(1).getBuren());
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

    private void Buren(int x, int y){
        char teken;
        if(puntenMap.get(x).get(y).getAlive()){
            teken = '+';
        }else teken = '-';

        if(x!=0){
            puntenMap.get(x-1).get(y).wijzigAantaBuren(teken);
            if(y!= hoogte-1){
                puntenMap.get(x-1).get(y+1).wijzigAantaBuren(teken);
            }
            if(y!=0){
                puntenMap.get(x-1).get(y-1).wijzigAantaBuren(teken);
            }
        }
        if(x!= breedte-1){
            puntenMap.get(x+1).get(y).wijzigAantaBuren(teken);
            if(y!=hoogte-1){
                puntenMap.get(x+1).get(y+1).wijzigAantaBuren(teken);
            }
            if(y!=0){
                puntenMap.get(x+1).get(y-1).wijzigAantaBuren(teken);
            }
        }
        if(y!=0){
            puntenMap.get(x).get(y-1).wijzigAantaBuren(teken);
        }
        if(y!=hoogte-1){
            puntenMap.get(x).get(y+1).wijzigAantaBuren(teken);
        }
    }

    public void randomCells() {
        Random rd = new Random();
        for(Map<Integer, Vakje> yCo : puntenMap.values()){
            for(Vakje vak: yCo.values()){
                vak.setAlive(rd.nextBoolean());
            }
        }
    }

}
