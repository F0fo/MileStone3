import java.util.ArrayList;

public class Meal {
    String ID;
    ArrayList<MealItem> mealItems;

    public Meal(String newID){
        this.ID = newID;
        this.mealItems = new ArrayList<MealItem>();
    }

    public void setID(String inID){
        this.ID = inID;
    }

    public String getID(){
        return this.ID;
    }



}
