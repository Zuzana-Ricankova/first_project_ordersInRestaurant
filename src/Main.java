import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import restaurant.*;



public class Main {
    public static void main(String[] args) throws OrderException {
        MenuManager menuManager = new MenuManager();

        try{
            menuManager.addDish(new Dish(1,"PRVNI", BigDecimal.valueOf(10), Duration.ofMinutes(10)));
            menuManager.addDish(new Dish(2,"DRUHY", BigDecimal.valueOf(10), Duration.ofMinutes(10)));
            menuManager.addDish(new Dish(3,"TRETI", BigDecimal.valueOf(10), Duration.ofMinutes(10)));
            menuManager.addDish(new Dish(4,"CTVRTY", BigDecimal.valueOf(10), Duration.ofMinutes(10)));
        }catch(OrderException e){
            System.err.println("Nastala chyba pri psani doby pripravy: " + ":\n" + e.getLocalizedMessage() + "\n");
        }

        System.out.println(menuManager.toString());

        OrderManager orderManager = new OrderManager();
        try{
            orderManager.addOrder(new Order(1, 3, menuManager.getDishes(1) , LocalDateTime.now()));
        }catch(OrderException e){
            System.err.println("Nastala chyba pri psani indexu jidla: " + ":\n" + e.getLocalizedMessage() + "\n");
        }



//        try{
//                menuList.add(new Dish("tdds", BigDecimal.valueOf(10), Duration.ofMinutes(-1)));
//
//        }catch (OrderException e){
//            System.err.println("Nastala chyba pri psani doby pripravy: " + ":\n" + e.getLocalizedMessage() + "\n");
//        }


        String fileName = Settings.getFileNameDishes();
        String fileNameOrders = Settings.getFileNameOrders();

        //nacitani ze souboru MENY - funguje
        try{
            menuManager.loadContentFromFile(fileName);
        }catch (OrderException e) {
            System.err.println("Error occurred while loading content from file: "+"menuManager.txt"+"\n"+e.getLocalizedMessage() + "\n");
        }

        //ukladani do souboru MENY
        try {
            menuManager.saveContentToFile(fileName);
        }catch (OrderException e){
            System.err.println("Error occurred while saving content to file: " + fileName + ":\n" + e.getLocalizedMessage());
        }

                //ukladani do souboru ORDERS
        try {
            orderManager.saveContentToFile(fileNameOrders);
        }catch (OrderException e){
            System.err.println("Error occurred while saving content to file: " + fileName + ":\n" + e.getLocalizedMessage());
        }


//                //nacitani ze souboru ORDERS
//        try{
//            orderManager.loadContentFromFile(fileNameOrders);
//        }catch (OrderException e) {
//            System.err.println("Error occurred while loading content from file: "+"menuManager.txt"+"\n"+e.getLocalizedMessage() + "\n");
//        }




    }}