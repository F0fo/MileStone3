import java.util.ArrayList;

public class Menu {
    String name;
    ArrayList<MenuSection> MenuSections;

    public Menu(String newName){
        this.name = newName;
        this.MenuSections = new ArrayList<MenuSection>();
    }

    public void addMenuSection(MenuSection inMenu){
        MenuSections.add(inMenu);
    }

    public void removeMenuSection(String sectionName){
        for (int i = 0; i < MenuSections.size() ; i++){
            if (MenuSections.get(i).name.toLowerCase() == sectionName.toLowerCase())
                MenuSections.remove(i);
        }

    }
    public String getName(){
        return this.name ;
    }
    public void setName(String inName){
        this.name = inName;
    }

}
