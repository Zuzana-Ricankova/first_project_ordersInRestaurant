package restaurant;

import java.io.*;
import java.time.DateTimeException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrderManager{
    public List<Order> orderList = new ArrayList<>();


    public List<Order> getOrderList() {
        return orderList;
    }
    public void addOrder(Order order){
        orderList.add(order);
    }

    public String getOrderCounts () {
        int ongoingOrdersCount = 0;
        int completedOrdersCount = 0;
        for (Order order : orderList) {
            if (order.getFulfilmentTime() != null) {
                completedOrdersCount++;
            } else if (order.getFulfilmentTime() == null) {
                ongoingOrdersCount++;
            }
        }return ("Number of pending orders:: " + ongoingOrdersCount + "\n"+
                "Number of completed orders: " + completedOrdersCount);
    }

    public void loadContentFromFile (String fileNameOrders, MenuManager menuManager) throws OrderException {
        int lineCounter = 0;
        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(fileNameOrders)))){
            while (scanner.hasNextLine()){
                lineCounter ++;
                String line = scanner.nextLine();
                String[] parts = line.split("; ");
                if (parts.length != 6) throw new OrderException( "Wrong number of items on line: " + lineCounter + " : "+ line + "!");
                int table = Integer.parseInt(parts[0]);
                Integer dishName = Integer.valueOf(parts[1]); //Dish chooseDish;
                int quantity = Integer.parseInt(parts[2]);
                LocalDateTime orderedTime = LocalDateTime.parse(parts[3]);;
                LocalDateTime fulfilmentTime = null;
                if (!parts[4].equals("null")) {
                    fulfilmentTime = LocalDateTime.parse(parts[4]); }
                Boolean isPayed = Boolean.valueOf(parts[5]);
                Dish chooseDish = menuManager.getDishes(dishName);
                Order order = new Order(table, chooseDish, quantity, orderedTime, fulfilmentTime, isPayed);
                orderList.add(order);

            }
        }
        catch (FileNotFoundException e) {
            throw new OrderException("File "+fileNameOrders+ "was not found!\n"+e.getLocalizedMessage());
        }catch (NumberFormatException e){
            throw new OrderException("Error when reading a numerical value on line: "+lineCounter+":\n"+e.getLocalizedMessage());
        }catch (IllegalArgumentException e){
            throw new OrderException("Error when reading the category on the number line: "+lineCounter+":\n"+e.getLocalizedMessage());
        }catch (DateTimeException e){
            throw new OrderException("Error when reading the date on the number line: "+lineCounter+":\n"+e.getLocalizedMessage());
        }
    }

    public void saveContentToFile (String fileNameOrders) throws OrderException {
        String delimiter = "; ";
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(fileNameOrders)))){
            for (Order order : orderList){
                writer.println(order.getTable() + delimiter +
                        order.getChooseDish() + delimiter +
                        order.getQuantity() + delimiter +
                        order.getOrderedTime() + delimiter +
                        order.getFulfilmentTime() + delimiter +
                        order.getPayed() + delimiter);
            }
        }catch (FileNotFoundException e){
            throw new OrderException("File " + fileNameOrders + "not found!\n" + e.getLocalizedMessage());
        }catch (IOException e){
            throw new OrderException("Output error when writing to file: " + fileNameOrders+ ":\n" +e.getLocalizedMessage());
        }
    }


public long numberOfCompletedOrders(){
    long numberOfOrders = 0;
    for (Order order : orderList){
        if(order.getFulfilmentTime()!= null){
        numberOfOrders ++;}
    }
    return numberOfOrders;
}

    public long getAverageOrderProcessingTime() throws OrderException {
        long totalProcessingTimeMinutes = 0;
        if (numberOfCompletedOrders() == 0) {
            throw new OrderException("No completed orders to calculate average processing time.");}
        for (Order order : orderList) {
            if (order.getFulfilmentTime() != null) {
                long lengthMinutes = Duration.between(order.getOrderedTime(), order.getFulfilmentTime()).toMinutes();
                totalProcessingTimeMinutes += lengthMinutes;
            }
            }return totalProcessingTimeMinutes / numberOfCompletedOrders();
        }

    public void printMenuItemsOrderedToday() {
        System.out.println("Dishes which have been ordered today:");
        LocalDate today = LocalDate.now();
        for (Order order : orderList) {
            LocalDate orderDate = order.getOrderedTime().toLocalDate();
            if(orderDate.equals(today)){
                System.out.println(order.getChooseDish().getTitle() + "\n");
            }
        }
}
    public void exportOrdersForTable(int tableNumber) {
        int number = 0;
        System.out.println("\n** Orders for table num. " + tableNumber + " **" + "\n" + "****");
        for (Order order : orderList) {
            if (tableNumber == order.getTable()) {
                ++number;
                System.out.println(number + ". " + order.toStringWithTableInfo());
            }
        }
    }

    @Override
    public String toString() {
        return orderList +"\n";
    }
}
