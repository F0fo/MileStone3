package models;
import java.util.ArrayList;

public class branch {
    String ID;
    String Address;
    Menu menu;
    ArrayList<Table> tables;
    ArrayList<Employee> employees; 
    public branch(String ID, String Address, String menuName)
    {
        this.ID = ID;
        this.Address = Address;
        this.menu = new Menu(menuName);   
        this.tables = new ArrayList<Table>(); 
        this.employees = new ArrayList<Employee>();
    }

    public String getID(){
        return this.ID;
    }   
    public String getAddress(){
        return this.Address;
    }

    public void setID(String inID){
        this.ID = inID;
    }

    public void setAddress(String inAddress){
        this.Address = inAddress;
    }
    public Menu getMenu(){
        return this.menu;
    }
    public ArrayList<Table> getTables(){
        return this.tables;
    }

}   

