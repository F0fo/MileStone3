
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
                    System.out.println("menu of branch to be added: ");
                    String tempMenu = userInput.nextLine();
                    findRestaurant(selectedRestaurant, restaurants).getBranches().add(new branch(tempID, tempAddress,tempMenu));
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
            System.out.println("branch Interface: "+ "\n" + "1. customer login" + "\n" + "2. Receptionist login"+ "\n" + "3. waiter login"+ "\n" + "4. menu" +"\n"+ "5. Tables" +"\n"+ "6. exit");
            switch (userInput.nextLine().toLowerCase()) {
                case "customer login":
                        inR = customerInterface(selectedRestaurant,selectedBranch, inR);
                    break;
                
                case"receptionist login":
                        inR = receptionInterface(selectedRestaurant, selectedBranch, inR);
                    break;
                case "waiter login":
                        inR = waiterInterface(selectedRestaurant, selectedBranch,inR);
                    break;
                case "menu":
                        inR = menuInterface(selectedRestaurant, selectedBranch, inR);
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
    public static ArrayList<Restaurant> customerInterface(String selectedRestaurant,String selectedBranch, ArrayList<Restaurant> inR){
        boolean awake = true;
        @SuppressWarnings("resource")
        Scanner userInput = new Scanner(System.in);
        System.out.println("Hope you enjoyed your meal!");
        
        while (awake) {
            System.out.println("Methods of payment: " +"\n"+ "1.cash" +"\n"+ "2.credit card" +"\n" + "3.chique" + "\n"+ "4. exit");
            switch (userInput.nextLine().toLowerCase()) {
                case "cash":
                    System.out.println("Our waiter will get you the reciept shortly");
                    break;
                case "credit card":
                    System.out.println("Our waiter will get you the machine shortly");
                    break;
                    case "chique":
                    System.out.println("our waiter will get you the paper shortly");
                    break;
                    case "exit":
                    awake = false;
                    break;
                default: System.out.println("Unknown paymenth method, please try again");
                    break;
            }
        }
        return inR;
    }

    public static ArrayList<Restaurant> receptionInterface(String selectedRestaurant,String selectedBranch, ArrayList<Restaurant> inR){
        boolean awake = true;
        @SuppressWarnings("resource")
        Scanner userInput = new Scanner(System.in);
        System.out.println("Welcome to the Mr/Mrs receptionist");
        while (awake){
            System.out.println("what would you like to do: " +"\n"+ "1. show tables" +"\n"+ "2. show available tables"+"\n"+ "3. reserve table"+"\n"+ "4. cancel reservation"+"\n"+ "5. add table"+"\n"+ "5. exit");
            switch (userInput.nextLine().toLowerCase()) {
                case "show tables":
                    System.out.println(getTables(selectedRestaurant, selectedBranch, inR));
                    break;
                case"show available tables":
                    System.out.println(getTablesAvailable(selectedRestaurant, selectedBranch, inR));
                break;
                case "reserve table":
                    System.out.println("which table would you like to reserve? insert tableNo");
                    reserveTable(userInput.nextLine(), selectedRestaurant, selectedBranch, inR);
                break;
                case "cancel reservation":
                    System.out.println("which table would you like to unreserve? insert tableNo");
                    unreserveTable(userInput.nextLine().toLowerCase(),selectedRestaurant, selectedBranch, inR);
                break;
                case "add table":
                    System.out.println("Enter the table nom: ");
                    String tempTableNom = userInput.nextLine();
                    System.out.println("Enter the seat count (Int)");
                    int tempSeatCount = userInput.nextInt();
                    addTable(tempTableNom,tempSeatCount,selectedRestaurant,selectedBranch,inR);
                    for (int i = 0; i < findTable(tempTableNom, selectedRestaurant, selectedBranch, inR).getSeatCount(); i++){
                        System.out.println("Enter the ID for the seat of the table");
                        findTable(tempTableNom, selectedRestaurant, selectedBranch, inR).getTableSeats().get(i).setSeatID(userInput.nextLine());
                    }
                break;
                case "exit":
                    awake = false;
                    break;
                default:
                    System.out.println("Unkown command, try again") ;
                    break;
            }
        }
        return inR;
    }

    public static String getTables(String selectedRestaurant,String selectedBranch, ArrayList<Restaurant> inR){
        String out= "Table registered: ";
        if (findBranch(selectedBranch, selectedRestaurant, inR).getTables().size() == 0)
            return "no tables registered";
        for (int i = 0; i < findBranch(selectedBranch, selectedRestaurant, inR).getTables().size(); i++){
            out = out + "ID: " + findBranch(selectedBranch,selectedRestaurant,inR).getTables().get(i).getTableNo() +" Seats: " + findBranch(selectedBranch,selectedRestaurant,inR).getTables().get(i).getSeatCount()+ ", ";
        }
        return out;
    }
    public static String getTablesAvailable(String selectedRestaurant,String selectedBranch, ArrayList<Restaurant> inR){
        String out = "Available tables: ";
        boolean available = false;
        for (int i = 0; i < findBranch(selectedBranch, selectedRestaurant, inR).getTables().size(); i++){   
            if (findBranch(selectedBranch, selectedRestaurant, inR).getTables().get(i).isAvailable() == true){
                available = true;
                out = out +"No: "+ findBranch(selectedBranch, selectedRestaurant, inR).getTables().get(i).getTableNo() + ", "; 
            }
        }
        if (available == false)
            return "No available tables";
        return out;
    }
    public static void reserveTable(String selectedTableNom, String selectedRestaurant,String selectedBranch, ArrayList<Restaurant> inR){
        for (int i = 0; i < findBranch(selectedBranch, selectedRestaurant, inR).getTables().size(); i++){
            if (findBranch(selectedBranch, selectedRestaurant, inR).getTables().get(i).getTableNo().toLowerCase().equals(selectedTableNom.toLowerCase())){
                findBranch(selectedBranch, selectedRestaurant, inR).getTables().get(i).setAvailable(false);
                System.out.println("Table is reserved");
                return;
            }
        }
        System.out.println("Table doesnt exist");
    }
    public static void addTable(String newTableNom,int newSeatCount, String selectedRestaurant,String selectedBranch, ArrayList<Restaurant> inR){
        findBranch(selectedBranch, selectedRestaurant, inR).getTables().add(new Table(newTableNom, newSeatCount,""));
    }
    public static void unreserveTable(String selectedTableNom, String selectedRestaurant,String selectedBranch, ArrayList<Restaurant> inR){
        for (int i = 0; i < findBranch(selectedBranch, selectedRestaurant, inR).getTables().size(); i++){
            if (findBranch(selectedBranch, selectedRestaurant, inR).getTables().get(i).getTableNo().toLowerCase().equals(selectedTableNom.toLowerCase())){
                findBranch(selectedBranch, selectedRestaurant, inR).getTables().get(i).setAvailable(true);
                System.out.println("table is available");
                return;
            }
        }
        System.out.println("Table doesnt exist");
    }
    public static boolean tableExists(String selectedTableNom, String selectedRestaurant,String selectedBranch, ArrayList<Restaurant> inR){
        for (int i = 0; i < findBranch(selectedBranch, selectedRestaurant, inR).getTables().size(); i++){
            if(findBranch(selectedBranch, selectedRestaurant, inR).getTables().get(i).getTableNo().toLowerCase().equals(selectedTableNom.toLowerCase())){
                return true;
            }
        }
        return false;

    }
    public static Table findTable(String selectedTableNom, String selectedRestaurant,String selectedBranch, ArrayList<Restaurant> inR){
        if (tableExists(selectedTableNom, selectedRestaurant, selectedBranch, inR) == true){
            for (int i = 0; i < findBranch(selectedBranch, selectedRestaurant, inR).getTables().size(); i++){ 
                if (findBranch(selectedBranch, selectedRestaurant, inR).getTables().get(i).getTableNo().toLowerCase().equals(selectedTableNom.toLowerCase())){
                    return findBranch(selectedBranch, selectedRestaurant, inR).getTables().get(i);
                }
            }
        }
        return null;
    }

    public static ArrayList<Restaurant> waiterInterface(String selectedRestaurant,String selectedBranch, ArrayList<Restaurant> inR){
        boolean awake = true;
        @SuppressWarnings("resource")
        Scanner userInput = new Scanner(System.in);
        System.out.println("Welcome to the system Mr/Mrs waiter");
        while (awake){
            System.out.println("What would you like to do?");
            switch (userInput.nextLine().toLowerCase()) {
                case "create order":
                        System.out.println("For which table does this order belong to? (tableNo)");
                        String tempTableNom = userInput.nextLine();
                        if (tableExists(tempTableNom, selectedRestaurant, selectedBranch, inR)){
                            Table tempTable = findTable(tempTableNom, selectedRestaurant, selectedBranch, inR);
                            System.out.println("what will be the orderID for the table?");
                            tempTable.getTableOrder().setID(userInput.nextLine());
                        }else{
                            System.out.println("table doesnt exist");
                            break;
                        }
                    break;
                case"add meal":
                        System.out.println("For which table does this meal belong to? (tableNo)");
                        String tempTableNomTwo = userInput.nextLine();
                        System.out.println("For which Seat does this meal belong to? (seatNo)");
                        String tempSeatNom = userInput.nextLine();
                        if (seatExists(tempTableNomTwo, tempSeatNom, selectedRestaurant, selectedBranch, inR) & tableExists(tempTableNomTwo, selectedRestaurant, selectedBranch, inR)){
                            System.out.println("What will the meal be for this seat?");
                            findTableSeat(tempTableNomTwo, tempSeatNom, selectedRestaurant, selectedBranch, inR).getSeatMeals().add(new Meal(userInput.nextLine()));
                        }
                            break;
                case"show meal":
                    System.out.println("For which table do you want to see the meals belong to? (tableNo)");
                    String tempTableNomThree = userInput.nextLine();
                    String out = "Meals of this table: ";
                    if (tableExists(tempTableNomThree, selectedRestaurant, selectedBranch, inR)){
                        for (int i = 0; i < findTable(tempTableNomThree, selectedRestaurant, selectedBranch, inR).getSeatCount(); i++ ){
                            out = out + findTable(tempTableNomThree, selectedRestaurant, selectedBranch, inR).getTableSeats().get(i).getSeatMeals().toString() +", ";
                       }
                       break;
                    }else{
                        System.out.print("Table doesnt exist, check again or add it");
                    }
                    break;
                default:
                    break;
            }
        }
        
        return inR;
    }
public static boolean seatExists(String selectedTableNom, String selectedSeatNom,String selectedRestaurant,String selectedBranch, ArrayList<Restaurant> inR){
    for (int i = 0; i < findTable(selectedTableNom, selectedRestaurant, selectedBranch, inR).getSeatCount(); i++){
        if(findTable(selectedTableNom, selectedRestaurant, selectedBranch, inR).getTableSeats().get(i).getSeatID().toLowerCase().equals(selectedTableNom.toLowerCase())){
            return true;
        }
    }
    return false;
}
public static TableSeat findTableSeat(String selectedTableNom, String selectedSeatNom,String selectedRestaurant,String selectedBranch, ArrayList<Restaurant> inR){
    for (int i = 0; i < findTable(selectedTableNom, selectedRestaurant, selectedBranch, inR).getSeatCount(); i++){
        if(findTable(selectedTableNom, selectedRestaurant, selectedBranch, inR).getTableSeats().get(i).getSeatID().toLowerCase().equals(selectedTableNom.toLowerCase())){
            return findTable(selectedTableNom, selectedRestaurant, selectedBranch, inR).getTableSeats().get(i);
        }
    }
    return null;
}


    public static ArrayList<Restaurant> menuInterface(String selectedRestaurant,String selectedBranch, ArrayList<Restaurant> inR){
        boolean awake = true;
        @SuppressWarnings("resource")
        Scanner userInput = new Scanner(System.in);
        System.out.println("Welcome to the Restaurant! ") ;
        while (awake){
            System.out.println("what would you like to do?" +"\n"+ "1. Show menu"+"\n"+ "2. show sections"+"\n"+ "3. show items"+"\n"+ "4. add section" +"\n"+"5. add item" +"\n"+ "6. exit" );
            switch (userInput.nextLine().toLowerCase()) {
               
                case "show menu":
                        
                    System.out.print(findBranch(selectedBranch, selectedRestaurant, inR).getMenu().getName());
                        
                    break;
                case "show sections": //will show all sections of a menu
                    System.out.println(showSections(selectedRestaurant, selectedBranch, inR));
                
                    break;
                case"show items": //will show all items of a section
                    System.out.println("Which section do you want to browse?");
                    String selectedSection = userInput.nextLine().toLowerCase();
                    
                    showItems(selectedRestaurant, selectedBranch, selectedSection, inR);
                    break;
                case "add section":
                    System.out.println("Name of new section you want to add?");
                    findBranch(selectedBranch, selectedRestaurant, inR).getMenu().addMenuSection(new MenuSection(userInput.nextLine()));
                    break;
                case "add item":
                    System.out.println("which section do you want to put this in?");
                    String tempMenuSection  = userInput.nextLine();
                    System.out.println("What is the name of the item");
                    String tempName = userInput.nextLine();
                    System.out.println("What is the main ingredient?");
                    String tempIngredient = userInput.nextLine();
                    System.out.println("What is the price in double?");
                    Double tempPrice = userInput.nextDouble();
                    findSection(tempMenuSection, selectedRestaurant, selectedBranch, inR).getMenuItems().add(new MenuItem(tempName,tempIngredient,tempPrice));
                    break;
                case "exit":
                    awake = false;
                    break;
                default: 
                    System.out.println("Unknown command, try again");
                    break;
            }
        }
        return inR;
    }

    public static String showSections(String selectedRestaurant,String selectedBranch, ArrayList<Restaurant> inR){
        //show all sections inside a menu
        String out = "Sections: ";
        if (findBranch(selectedBranch, selectedRestaurant, inR).getMenu().getMenuSections().size() == 0){
            return "No sections";
        }
        for(int i = 0; i< findBranch(selectedBranch, selectedRestaurant, inR).getMenu().getMenuSections().size(); i ++){
            out = out + findBranch(selectedBranch, selectedRestaurant, inR).getMenu().getMenuSections().get(i).getName() + ", ";
        }
        return out;
    }
    public static int findSectionLoc(String sectionName,String selectedRestaurant, String selectedBranch, ArrayList<Restaurant>inR ){
        //finds the location of a specific section by name
        for (int i = 0; i < findBranch(selectedBranch, selectedRestaurant, inR).getMenu().getMenuSections().size(); i++){
        if(findBranch(selectedBranch, selectedRestaurant, inR).getMenu().getMenuSections().get(i).getName().toLowerCase().equals(sectionName.toLowerCase()))
            return i;
        }
        return -1;
    }
    public static MenuSection findSection(String sectionName,String selectedRestaurant, String selectedBranch, ArrayList<Restaurant>inR ){
        //finds the object menuSections by its location
        int temp= findSectionLoc(sectionName, selectedRestaurant, selectedBranch, inR);
        return findBranch(selectedBranch, selectedRestaurant, inR).getMenu().getMenuSections().get(temp);
    }

    public static String showItems(String selectedRestaurant,String selectedBranch,String selectedSection ,ArrayList<Restaurant> inR){
        String out = "Items: ";
        if (findSection(selectedSection, selectedRestaurant, selectedBranch, inR).getMenuItems().size() == 0){
            return "No items";
        }
        for (int i = 0; i < findSection(selectedSection, selectedRestaurant, selectedBranch, inR).getMenuItems().size(); i++){
            out  = out + findSection(selectedSection, selectedRestaurant, selectedBranch, inR).getMenuItems().get(i).getName() + ", ";
        }
        return out;

    }

}
