import java.util.ArrayList;

public class Order {
    String ID;
    ArrayList<Meal> meals;
    public Order(String newID){
        this.ID = newID;
        this.meals = new ArrayList<Meal>();
    }

    public String getID(){
        return this.ID;
    }
    public void setID(String newID){
        this.ID = newID;
    }
}
