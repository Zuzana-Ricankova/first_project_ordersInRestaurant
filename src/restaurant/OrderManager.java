package restaurant;

import java.io.*;
import java.math.BigDecimal;
import java.time.DateTimeException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrderManager {
    public List<Order> orderList = new ArrayList<>();

    public List<Order> getOrderList() {
        return orderList;
    }
    public void addOrder(Order order){
        orderList.add(order);
    }

//    public void loadContentFromFile (String fileNameOrders) throws OrderException {
//        int lineCounter = 0;
//        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(fileNameOrders)))){
//            while (scanner.hasNextLine()){
//                lineCounter ++;
//                String line = scanner.nextLine();
//                String[] parts = line.split("; ");
//                if (parts.length != 6) throw new OrderException( "Wrong number of items on line: " + lineCounter + " : "+ line + "!");
//                int table = Integer.parseInt(parts[0]);
//                Dish chooseDish = parts[1]; //Dish chooseDish;
//                int quantity = Integer.parseInt(parts[2]);
//                LocalDateTime orderedTime = LocalDateTime.parse(parts[3]);
//                LocalDateTime fulfilmentTime = LocalDateTime.parse(parts[4]);
//                Boolean isPayed = Boolean.valueOf(parts[5]);
//                Order order = new Order(table, chooseDish, quantity, orderedTime, fulfilmentTime, isPayed);
//                orderList.add(order);
//
//            }
//        }
//        catch (FileNotFoundException e) {
//            throw new OrderException("File "+fileNameOrders+ "was not found!\n"+e.getLocalizedMessage());
//        }catch (NumberFormatException e){
//            throw new OrderException("Error when reading a numerical value on line: "+lineCounter+":\n"+e.getLocalizedMessage());
//        }catch (IllegalArgumentException e){
//            throw new OrderException("Error when reading the category on the number line: "+lineCounter+":\n"+e.getLocalizedMessage());
//        }catch (DateTimeException e){
//            throw new OrderException("Error when reading the date on the number line: "+lineCounter+":\n"+e.getLocalizedMessage());
//        }
//    }

    public void saveContentToFile (String fileNameOrders) throws OrderException {
        String delimiter = "; ";
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(fileNameOrders)))){
            for (Order order : orderList){
                writer.println(order.getTable() + delimiter +
                        order.getChooseDish() + delimiter +
                        order.getQuantity() + delimiter+
                                order.getOrderedTime() + delimiter+
                                order.getFulfilmentTime() + delimiter +
                        order.getPayed() + delimiter);
            }
        }catch (FileNotFoundException e){
            throw new OrderException("File " + fileNameOrders + "not found!\n" + e.getLocalizedMessage());
        }catch (IOException e){
            throw new OrderException("Output error when writing to file: " + fileNameOrders+ ":\n" +e.getLocalizedMessage());
        }
    }
    @Override
    public String toString() {
        return "OrderManager: " +
                "orderList:" + orderList;
    }
}
