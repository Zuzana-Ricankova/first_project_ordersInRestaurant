import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.Comparator;

import restaurant.*;


public class Main {
    public static void main(String[] args) throws OrderException {
        MenuManager menuManager = new MenuManager();
        OrderManager orderManager = new OrderManager();


        //1. Načti stav evidence z disku.
        String fileNameDishes = Settings.getFileNameDishes();
        String fileNameOrders = Settings.getFileNameOrders();
        try{
            menuManager.loadContentFromFile(fileNameDishes);
        }catch (OrderException e) {
            System.err.println("Error occurred while loading content from file: "+ fileNameDishes +"\n"+e.getLocalizedMessage() + "\n");
        }

        try{
            orderManager.loadContentFromFile(fileNameOrders, menuManager);
        }catch (OrderException e) {
            System.err.println("Error occurred while loading content from file: "+ fileNameOrders +"\n"+e.getLocalizedMessage() + "\n");
        }

        //2. Připrav testovací data.
        try {
            menuManager.addDish(new Dish(1, "Kuřecí řízek obalovaný 150 g", BigDecimal.valueOf(150), Duration.ofMinutes(15), "kureci-rizek-obalovany"));
            menuManager.addDish(new Dish(2, "Hranolky 150 g", BigDecimal.valueOf(50), Duration.ofMinutes(10), "hranolky"));
            menuManager.addDish(new Dish(3, "Pstruh na víně 200 g", BigDecimal.valueOf(200), Duration.ofMinutes(15), "pstruh-na-vine"));
            menuManager.addDish(new Dish(4, "Kofola 0,5 l", BigDecimal.valueOf(20), Duration.ofMinutes(5)));
        } catch (OrderException e) {
            System.err.println("An error occurred while writing the preparation time: " + ":\n" + e.getLocalizedMessage() + "\n");
        }

        try {
            orderManager.addOrder(new Order(15, 2, menuManager.getDishes(1), LocalDateTime.of(2024, 3,3,12,15)));
            orderManager.addOrder(new Order(15, 2, menuManager.getDishes(2), LocalDateTime.of(2024, 3,3,12,15)));
            orderManager.addOrder(new Order(15, 2, menuManager.getDishes(4), LocalDateTime.of(2024, 3,3,12,15), LocalDateTime.of(2024,3,3,12,20)));
            orderManager.addOrder(new Order(2, 2, menuManager.getDishes(3), LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES).withSecond(0).withNano(0)));
            orderManager.addOrder(new Order(5, menuManager.getDishes(1),1,  LocalDateTime.of(2024,2,2,14,15), LocalDateTime.of(2024,2,2,14,40), true));
        } catch (OrderException e) {
            System.err.println("An error occurred when writing the order or fulfilment time: " + "\n" + e.getLocalizedMessage() + "\n");
        }

        //3. Vypiš celkovou cenu konzumace pro stůl číslo 15.
        BigDecimal fullPrice = BigDecimal.ZERO;
        for(Order order : orderManager.orderList){
            if(order.getTable()==15){
            fullPrice = fullPrice.add(order.getChooseDish().getPrice().multiply(BigDecimal.valueOf(order.getQuantity())));
        }}
        System.out.println("Complete price for table 15: " + fullPrice + " Kč");

        /*4. všechny připravené metody pro managment
                1. Kolik objednávek je aktuálně rozpracovaných a nedokončených
         */
        System.out.println("\n" + orderManager.getOrderCounts());

        //4.2. Možnost seřadit objednávky podle času zadání.
        Collections.sort(orderManager.orderList, Comparator.comparing(Order::getOrderedTime));
        System.out.println("\nSort orders by ordered time:");
        orderManager.orderList.forEach(System.out::println);

        //4.3 Průměrnou dobu zpracování objednávek.
        try {
            System.out.println("\nAverage order processing time: " + orderManager.getAverageOrderProcessingTime() + "\n");
        } catch (OrderException e) {
            System.err.println("An error occurred when calculating the average of processing time of orders: " + ":\n" + e.getLocalizedMessage() + "\n");
        }


        /* 4.4.Seznam jídel, která byla dnes objednána.
                Bez ohledu na to, kolikrát bylo dané jídlo objednáno.
         */
        orderManager.printMenuItemsOrderedToday();

        //4.5 Export seznamu objednávek pro jeden stůl ve formátu viz materialy
        orderManager.exportOrdersForTable(15);
        orderManager.exportOrdersForTable(5);

        //5. Změněná data ulož na disk.
        try {
            menuManager.saveContentToFile(fileNameDishes);
        }catch (OrderException e){
            System.err.println("Error occurred while saving content to file: " + fileNameDishes + ":\n" + e.getLocalizedMessage());
        }

        try {
            orderManager.saveContentToFile(fileNameOrders);
        }catch (OrderException e){
            System.err.println("Error occurred while saving content to file: " + fileNameOrders + ":\n" + e.getLocalizedMessage());
        }

        //6. Po opětovném spuštění aplikace musí být data opět v pořádku načtena.
        try{
            menuManager.loadContentFromFile(fileNameDishes);
        }catch (OrderException e) {
            System.err.println("Error occurred while loading content from file: "+ fileNameDishes +"\n"+e.getLocalizedMessage() + "\n");
        }

        try{
            orderManager.loadContentFromFile(fileNameOrders, menuManager);
        }catch (OrderException e) {
            System.err.println("Error occurred while loading content from file: "+ fileNameOrders +"\n"+e.getLocalizedMessage() + "\n");
        }
    }
    }