import java.util.ArrayList;

public class branch {
    String ID;
    String Address;
    ArrayList<Menu> menus;
    ArrayList<Table> tables;
    ArrayList<Employee> employees; 
    public branch(String ID, String Address)
    {
        this.ID = ID;
        this.Address = Address;
        this.menus = new ArrayList<Menu>();   
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

}   

