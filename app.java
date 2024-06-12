
import models.*;
import java.util.ArrayList;

import java.util.Scanner;


public class app {
    public static void main(String[] args) 
    {
        boolean awake = true;
        Scanner userIn = new Scanner(System.in); 
        ArrayList<Restaurant> resturaunts = new ArrayList<Restaurant>();
        System.out.println("Welcome to the system");
        while (awake) //aplication running
        {
            System.out.println("\n" + "Main interface:" + "\n" + "1. restaurant interface"  + "\n" + "2. branch interface"  + "\n" + "3. login reception"  + "\n" +"4. login manager"  + "\n" +"5. exit");
            switch (userIn.nextLine().toString().toLowerCase()) 
            {
                case "restaurant interface":
                    resturaunts = restaurantInterface(resturaunts);
                    break;
                case "branch interface":
                    System.out.println("For which restaurant would you like to look at their branches?");
                    String temp = userIn.nextLine();
                    if (restaurantExists(resturaunts, temp)){
                        resturaunts = branchesInterface(resturaunts, temp);
                    }else{
                        System.out.println("Sorry doesnt exist, try again");
                    }
                    break;
                case"exit":
                    awake = false;
                    break;
                default:
                System.out.println("unkown please try agian: ");
                break;
                
            }
            
        }
        userIn.close();
        System.out.println("Have a good day! ");
        
    }
    

    public static ArrayList<Restaurant> restaurantInterface(ArrayList<Restaurant> rest){
        boolean adminAwake = true;
        @SuppressWarnings("resource")
        Scanner adminIn = new Scanner(System.in); 
        System.out.println("You are now in the Restaurant Interface, to go back to the main menu. Enter Exit ");
        while (adminAwake) {
            System.out.println("RestaurantInterface: "+ "\n" + "1. add" + "\n" + "2. remove" + "\n" + "3. show"+ "\n" + "4. exit");
            switch (adminIn.nextLine().toLowerCase()) {
                case "add":
                    System.out.println("Name of restaurant to be added: ");                  
                    addRestaurant(adminIn.nextLine(), rest);
                    break;
                case"remove":
                    System.out.println("Name of restaurant to be removed: ");  
                    String temp = adminIn.nextLine();
                    System.out.println("the inputed Restaurant to be removed is: " + temp);
                    removeRestaurant(temp, rest);
                    break;
                case"show":
                    System.out.println("Registered Restaurants: " + getRestaurants(rest));
                    break;
                case"find": //debugging command
                        System.out.println((findRestaurantLoc(adminIn.nextLine(), rest)));
                    break;
                case"exit":
                    adminAwake = false;
                    break;
                default:
                    System.out.println("unkown please try agian: ");
                    break;
            }
            
        }
        
        return rest;
    }

    public static String getRestaurants(ArrayList<Restaurant> inR){ 
        //print restaurant names
        if (inR.size() == 0)
            return "no resturaunts registered";
        String out = "";
        for (int i = 0; i < inR.size(); i++){
            out = out + inR.get(i).getName()+", ";
        }
        return out;
    }

    public static boolean restaurantExists(ArrayList<Restaurant> inR, String inName){
        if (findRestaurantLoc(inName, inR) == -1)
            return false;
        return true;
    }
    public static void addRestaurant(String name, ArrayList<Restaurant> inR){ 
        //add a restaurant to array list
    inR.add(new Restaurant(name));    
    }

    public static void removeRestaurant(String name, ArrayList<Restaurant> inR){
        //remove restaurant to array list
        if (findRestaurantLoc(name, inR) == -1){
            System.out.println("Error: restaurant name not found");
            return;
        }
        inR.remove(findRestaurantLoc(name, inR));

    }
    public static Restaurant findRestaurant(String name,ArrayList<Restaurant> inR){
        //find the restuaruant by name from array list, return null otherwise
        int temp = findRestaurantLoc(name, inR);
        return inR.get(temp);
        
    }

    public static int findRestaurantLoc(String name, ArrayList<Restaurant> inR){
        //find the location of restaurrant in restaurant array
        for (int i=0; i<inR.size(); i++){
            //System.out.println("Current restaurant about to be checked: "+inR.get(i).getName());
            if (inR.get(i).getName().toLowerCase().equals(name.toLowerCase())){
                
                return i;
            }
        }
        return -1;
    }


    public static ArrayList<Restaurant> branchesInterface(ArrayList<Restaurant> restaurants, String selectedRestaurant){ 
        //we have the array list of restaurants and the name of restaurant NOT object
        boolean awake = true;
        @SuppressWarnings("resource")
        Scanner userInput = new Scanner(System.in); 
        System.out.println("You are now in the branch Interface, to go back to the main menu. Enter Exit ");
        while (awake){
            System.out.println("branch Interface: "+ "\n" + "1. add" + "\n" + "2. remove" + "\n" + "3. show"+"\n"+ "4. branch login"+ "\n" + "5. exit");
            switch (userInput.nextLine().toLowerCase()) {
                case "show":
                    System.out.println(getBranches(restaurants, selectedRestaurant));
                    break;
                case "add":
                    System.out.println("ID of branch to be added: ");
                    String tempID = userInput.nextLine();
                    System.out.println("Address of branch to be added: ");
                    String tempAddress = userInput.nextLine();
                    findRestaurant(selectedRestaurant, restaurants).getBranches().add(new branch(tempID, tempAddress));
                    break;
                case "remove":
                    System.out.println("ID of branch to be removed: ");
                    tempID = userInput.next();
                    removeBranch(tempID, selectedRestaurant, restaurants);
                    break;
                case "branch login":
                    System.out.println("For which branch would you like to log in: ");
                    String selectedBranch = userInput.nextLine();
                    if(branchExists(selectedRestaurant,selectedBranch, restaurants)){
                        restaurants = branchInterface(selectedRestaurant,selectedBranch,restaurants);
                    }else{
                        System.out.println("branch doesnt exist");
                    }
                    break;
                case "exit":
                    awake = false;
                    break;
                default:
                    System.out.println("unkown please try agian: ");
                    break;
            }
        }
        return restaurants;
    }
    public static String getBranches(ArrayList<Restaurant> inR, String selectedRestaurant){
        String out ="Registered branches: ";
        if (findRestaurant(selectedRestaurant, inR).getBranches().size() == 0)
            return "no branches registered";
        for (int i = 0; i < findRestaurant(selectedRestaurant,inR).getBranches().size(); i++){
            out = out + "ID: " +findRestaurant(selectedRestaurant,inR).getBranches().get(i).getID() + " Address: "+findRestaurant(selectedRestaurant,inR).getBranches().get(i).getAddress()+ ", " ;
        }
        return out;
    }
    public static branch findBranch(String searchID,String restName,ArrayList<Restaurant> inR){
        //find branch by branch id and resturant name. return nill otherwise
        Restaurant chosen = findRestaurant(restName, inR); //find restaurant
        for (int i = 0; i <chosen.getBranches().size(); i++) { //find branch of restaurant
            if (chosen.getBranches().get(i).getID().toLowerCase().equals(searchID.toLowerCase()))
                return chosen.getBranches().get(i);
        }
        return null;
    } 
    public static int findBranchLoc(String restName, String searchID,ArrayList<Restaurant> inR ){
        //find the location of the branch in array list branches of the restauraunt
        int temp =-1;
        Restaurant chosen = findRestaurant(restName, inR); //find restaurant object
        for (int i = 0; i <chosen.getBranches().size(); i++) { //find branch of restaurant
            if (chosen.getBranches().get(i).getID().toLowerCase().equals(searchID.toLowerCase()) ){
                temp = i;
            }
                
        }
        return temp;
    }
    public static void removeBranch(String searchID,String restName,ArrayList<Restaurant> inR){
        if (findBranchLoc(restName,searchID, inR) == -1){
            System.out.println("Branch doent exit");
            return;
        }
        Restaurant chosen = findRestaurant(restName, inR);
        chosen.getBranches().remove(findBranchLoc(restName, searchID, inR));
    }
    public static boolean branchExists(String restName,String branchID, ArrayList<Restaurant> inR){
        
        if (findBranchLoc(restName, branchID, inR) == -1)
            return false;

       return true;
    }

    public static ArrayList<Restaurant> branchInterface(String selectedRestaurant,String selectedBranch, ArrayList<Restaurant> inR){
        boolean awake = true;
        @SuppressWarnings("resource")
        Scanner userInput = new Scanner(System.in);
        System.out.println("You are now in the branch Interface of a specific branch");

        while (awake){
            System.out.println("branch Interface: "+ "\n" + "1. customer login" + "\n" + "2. Receptionist login"+ "\n" + "3. waiter login"+ "\n" + "4. menu" +"\n"+ "5. Exit");
            switch (userInput.nextLine().toLowerCase()) {
                case "customer login":
                    
                    break;
                
                case"receptionist login":

                    break;
                case "waiter login":

                    break;
                case "menu":

                    break;
                case "exit":
                    awake = false;
                    break;

            
                default:
                    System.out.println("unkown please try agian: ");
                    break;
            }
        }
        return inR;
    }


}
