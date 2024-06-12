package models;
import java.util.ArrayList;

public class TableSeat {
    String seatID;
    ArrayList<Meal> seatMeals;

    public TableSeat(String inID){
        this.seatID = inID;
        this.seatMeals = new ArrayList<Meal>(); 
    }

    public void setSeatID(String inID){
            this.seatID = inID;
    }

    public String getSeatID(){

        return this.seatID;
    }
    public ArrayList<Meal> getSeatMeals(){
        return this.getSeatMeals();
    }

}
