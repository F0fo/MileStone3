package models;
import java.util.ArrayList;

public class TableSeat {
    String seatID;
    ArrayList<Meal> seatMeal;

    public TableSeat(String inID){
        this.seatID = inID;
        this.seatMeal = new ArrayList<Meal>(); 
    }

    public void setSeatID(String inID){
            this.seatID = inID;
    }

    public String getSeatID(){

        return this.seatID;
    }

}
