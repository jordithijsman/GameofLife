package sample.model;

public class Vakje {

    private Boolean alive;
    private int buren;

    Vakje(){
        this.alive = false;
        this.buren = 0;
    }

    public void setAlive(boolean bool){
        this.alive = bool;
    }

    public Boolean getAlive(){
        return this.alive;
    }

}
