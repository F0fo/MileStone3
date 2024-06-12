package models;
import java.util.ArrayList;

public class MenuSection {
    String name;
    ArrayList<MenuItem> menuItems;

    public MenuSection(String inName){
        this.menuItems = new ArrayList<MenuItem>();
        this.name = inName;
    }

    public String getName(){
        return this.name;
    }
    public void setName(String inname){
        this.name = inname;
    }

    public void addMenuItem(MenuItem newItem){
        menuItems.add(newItem);
    }
    public void setMenuItem(MenuItem newItem){
        for (int i = 0; i < menuItems.size() ; i++)
            if (menuItems.get(i).name == newItem.name)
                menuItems.set(i, newItem);
    }
    public ArrayList<MenuItem> getMenuItems(){
        return this.menuItems;
    }
}
