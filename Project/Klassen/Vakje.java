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

    public void setBuren(int buren){
        this.buren = buren;
    }

    public void wijzigAantaBuren(char teken){
        if(teken == '+'){
            buren+=1;
        }if(teken == '-' && buren !=0){
            buren-=1;
        }
    }

    public Boolean getAlive() {
        return this.alive;
    }

    public int getBuren(){
        return this.buren;
    }

}
