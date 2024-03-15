package restaurant;

import java.io.*;
import java.math.BigDecimal;
import java.time.DateTimeException;
import java.time.Duration;
import java.util.*;


public class MenuManager {
    public List <Dish> menuList = new ArrayList<>();


    public void removeDishByID(Integer dishID) throws OrderException {
        for (Dish dish :menuList) {
            if (dishID.equals(dish.getID())) {
                menuList.remove(dish);
                resetMenuListIDs();
                return;
            }
        }
        throw new OrderException("Dish with ID: " + dishID + " doesn't exist!");
    }

    private void resetMenuListIDs() {
        Integer posledniID = 0;
        for (Dish dish : menuList) {
            dish.setID(++posledniID);
        }
    }


    public Dish getDishes(Integer index) throws OrderException{
        for(Dish dish : menuList){
            if(dish.getID().equals(index)) {
                return dish;
        }
    }throw new OrderException("Wrong number of dish: " + index + "!" + " It doesn't exist!");
    }

    public void addDish(Dish dish){
        menuList.add(dish);
    }


            public void loadContentFromFile (String fileName) throws OrderException {
                int lineCounter = 0;
                try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(fileName)))){
                    while (scanner.hasNextLine()){
                        lineCounter ++;
                        String line = scanner.nextLine();
                        String[] parts = line.split("; ");
                        if (parts.length != 5) throw new OrderException( "Wrong number of items on line: " + lineCounter + " : "+ line + "!");
                        Integer ID = Integer.valueOf(parts[0]);
                        String title = parts[1];
                        BigDecimal price = new BigDecimal(parts[2]);
                        Duration preparationTime = Duration.parse(parts[3]);
                        String imageName = parts[4];
                        Dish dish = new Dish(ID, title, price, preparationTime, imageName);
                        menuList.add(dish);
                    }
                }
                catch (FileNotFoundException e) {
                    throw new OrderException("File "+fileName+ "was not found!\n"+e.getLocalizedMessage());
                }catch (NumberFormatException e){
                    throw new OrderException("Error when reading a numerical value on line: "+lineCounter+":\n"+e.getLocalizedMessage());
                }catch (IllegalArgumentException e){
                    throw new OrderException("Error when reading the category on the number line: "+lineCounter+":\n"+e.getLocalizedMessage());
                }catch (DateTimeException e){
                    throw new OrderException("Error when reading the date on the number line: "+lineCounter+":\n"+e.getLocalizedMessage());
                }
            }

            public void saveContentToFile (String fileName) throws OrderException {
                String delimiter = "; ";
                try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(fileName)))){
                    for (Dish dish : menuList){
                        writer.println(dish.getID() + delimiter +
                                dish.getTitle() + delimiter +
                                dish.getPrice() + delimiter +
                                dish.getPreparationTime() + delimiter +
                                dish.getImageName() + delimiter);
                    }
                }catch (FileNotFoundException e){
                    throw new OrderException("File " + fileName + "not found!\n" + e.getLocalizedMessage());
                }catch (IOException e){
                    throw new OrderException("Output error when writing to file: " + fileName + ":\n" +e.getLocalizedMessage());
                }
            }

            @Override
            public String toString() {
                return "MenuManager{" +
                        "menu=" + menuList +
                        ",\n";
            }
        }