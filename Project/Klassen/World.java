package sample.model;

import com.sun.prism.paint.RadialGradient;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class World {

    private int breedte;
    private int hoogte;
    private Map<Integer, Vakje> yMap = new HashMap<>();
    private Map<Integer, Map<Integer, Vakje>> puntenMap = new HashMap<>();

    public World(File file) throws FileNotFoundException, BestandOngeldigException {
        Scanner sc = new Scanner(file);
        int k = 0;
        if (sc.hasNextLine()) {

            this.hoogte = sc.nextInt();
            this.breedte = sc.nextInt();

        }
        sc.nextLine();
        for (int x = 0; x < this.breedte; x++) {
            puntenMap.put(x, new HashMap<>());
            if (sc.hasNextLine()) {
                String lijn = sc.nextLine();
                k++;
                if (lijn.length() == this.hoogte) {
                    for (int y = 0; y < lijn.length(); y++) {
                        char cell = lijn.charAt(y);
                        puntenMap.get(x).put(y, new Vakje());
                        Vakje test = puntenMap.get(x).get(y);
                        if (lijn.charAt(y) == '.') {
                            puntenMap.get(x).get(y).setAlive(false);
                        } else if (lijn.charAt(y) == 'O') {
                            puntenMap.get(x).get(y).setAlive(true);
                        } else throw new BestandOngeldigException("Ongeldig bestand, verkeerd teken gebruikt!");
                    }
                } else {
                    throw new BestandOngeldigException("Ongeldig bestand, hoogte is niet correct!");
                }
            }
        }
        if (k != breedte) {
            throw new BestandOngeldigException("Ongeldig bestand, breedte is niet correct!");
        }

        sc.close();
        alleBuren();

    }

    public World(int width, int height) {
        this.breedte = width;
        this.hoogte = height;
        for (int x = 0; x < breedte; x++) {
            puntenMap.put(x, new HashMap<>());
            for (int y = 0; y < hoogte; y++) {
                puntenMap.get(x).put(y, new Vakje());
            }
        }
    }


    public int getWidth() {
        return this.breedte;
    }

    public int getHeight() {
        return this.hoogte;
    }


    public void toggleCell(int x, int y) {
        if (puntenMap.get(x).get(y).getAlive()) {
            puntenMap.get(x).get(y).setAlive(false);
        } else {
            puntenMap.get(x).get(y).setAlive(true);
        }
    }


    public void nextGeneration() {
        alleBuren();
        World hulpWereld = new World(this.breedte, this.hoogte);
        hulpWereld.puntenMap.putAll(puntenMap);

        for (int x = 0; x < this.breedte; x++) {
            for (int y = 0; y < this.hoogte; y++) {
                Vakje teBekijken = this.puntenMap.get(x).get(y);
                Vakje aanTePassen = hulpWereld.puntenMap.get(x).get(y);
                if (teBekijken.getAlive() && teBekijken.getBuren() <= 1) {
                    aanTePassen.setAlive(false);
                } else if (teBekijken.getAlive() && teBekijken.getBuren() >= 4) {
                    aanTePassen.setAlive(false);
                } else if ((!teBekijken.getAlive()) && teBekijken.getBuren() == 3) {
                    aanTePassen.setAlive(true);
                }
            }
        }
        this.puntenMap.putAll(hulpWereld.puntenMap);
    }

    public boolean isAliveAt(int x, int y) {
        return puntenMap.get(x).get(y).getAlive();
    }

    private void telBuren(int x, int y) {
        int aantalBuren = 0;

        // x-1
        if (x != 0) {
            if (puntenMap.get(x - 1).get(y).getAlive()) {
                aantalBuren++;
            }

            if (y != this.hoogte - 1 && puntenMap.get(x - 1).get(y + 1).getAlive()) {
                aantalBuren++;
            }
            if (y != 0 && puntenMap.get(x - 1).get(y - 1).getAlive()) {
                aantalBuren++;
            }
        }
        //x+1
        if (x != this.breedte - 1) {
            if (puntenMap.get(x + 1).get(y).getAlive()) {
                aantalBuren++;
            }
            if (y != this.hoogte - 1 && puntenMap.get(x + 1).get(y + 1).getAlive()) {
                aantalBuren++;
            }
            if (y != 0 && puntenMap.get(x + 1).get(y - 1).getAlive()) {
                aantalBuren++;
            }
        }
        if (y != 0 && puntenMap.get(x).get(y - 1).getAlive()) {
            aantalBuren++;
        }
        if (y != this.hoogte - 1 && puntenMap.get(x).get(y + 1).getAlive()) {
            aantalBuren++;
        }
        puntenMap.get(x).get(y).setBuren(aantalBuren);
    }

    private void alleBuren() {
        for (int x = 0; x < this.breedte; x++) {
            for (int y = 0; y < this.hoogte; y++) {
                telBuren(x, y);
            }
        }
    }


    public void randomCells() {
        Random rd = new Random();
        for (int x = 0; x < this.breedte; x++) {
            for (int y = 0; y < this.hoogte; y++) {
                puntenMap.get(x).get(y).setAlive(rd.nextBoolean());

            }
        }
        alleBuren();
    }

    public void saveToFile(File file) throws IOException {
        FileWriter out = new FileWriter(file);
        StringBuilder output = new StringBuilder(this.hoogte + " " + this.breedte + "\n");
        for (int x = 0; x < this.breedte; x++) {
            for (int y = 0; y < this.hoogte; y++) {
                if (puntenMap.get(x).get(y).getAlive()) {
                    output.append("O");
                } else {
                    output.append(".");
                }
            }
            output.append("\n");
        }
        out.write(output.toString());
        out.close();
    }

}
