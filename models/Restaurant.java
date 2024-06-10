package models;
import java.util.ArrayList;

public class Restaurant {

    String name;
    ArrayList <branch> branches;

    
    public Restaurant(String name)
    {
        this.name = name;
        this.branches = new ArrayList<branch>();
    }

    public String getName()
    {
        return this.name ;
    }
    public void setName(String newName)
    {
        this.name = newName;
    }

    public void addBranch(String ID, String Address)
    {
        branch newBranch = new branch(ID, Address);
        branches.add(newBranch);    
    }

    public void removeBranch(String ID)
    {
        for (int i = 0; i <branches.size(); i++)
        {
            if (branches.get(i).ID.toLowerCase() == ID.toLowerCase()){
                branches.remove(i);
            }
        }

    }
    

}