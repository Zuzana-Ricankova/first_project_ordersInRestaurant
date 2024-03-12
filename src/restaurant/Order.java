
package restaurant;

import java.time.LocalDateTime;

public class Order {

    private int table;
    private int quantity;
    private Dish chooseDish;
    private LocalDateTime orderedTime;
    private LocalDateTime fulfilmentTime;
    private Boolean isPayed;


    public Order(int table, Dish chooseDish, int quantity, LocalDateTime orderedTime, LocalDateTime fulfilmentTime, Boolean isPayed) throws OrderException{
        this.table = table;
        this.quantity = quantity;
        this.chooseDish = chooseDish;
        this.orderedTime = orderedTime;
        this.fulfilmentTime = fulfilmentTime;
        this.isPayed = isPayed;
    }

    public Order(int table, int quantity, Dish chooseDish, LocalDateTime orderedTime) throws OrderException{
        this(table, chooseDish,quantity, orderedTime,null, false);
    }

    public Order(int table, int quantity, Dish chooseDish, LocalDateTime orderedTime, LocalDateTime fulfilmentTime) throws OrderException{
        this(table, chooseDish,quantity, orderedTime, fulfilmentTime, false);
    }

    public Order(int table, int quantity, Dish chooseDish, LocalDateTime orderedTime, Boolean isPayed) throws OrderException{
        this(table, chooseDish,quantity, orderedTime, null, true);

    }

    public Dish getChooseDish() {
        return chooseDish;
    }

    public int getTable() {
        return table;
    }

    public void setTable(int table) {
        this.table = table;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getOrderedTime() {
        return orderedTime;
    }

    public void setOrderedTime(LocalDateTime orderedTime) {
        this.orderedTime = orderedTime;
    }

    public LocalDateTime getFulfilmentTime() {
        return fulfilmentTime;
    }

    public void setFulfilmentTime(LocalDateTime fulfilmentTime) {
        this.fulfilmentTime = fulfilmentTime;
    }

    public Boolean getPayed() {
        return isPayed;
    }

    public void setPayed(Boolean payed) {
        isPayed = payed;
    }


    @Override
    public String toString() {
        return "Order{" +
                "table: " + table +
                ", quantity: " + quantity +
                ", chooseDish: " + chooseDish +
                ", orderedTime: " + orderedTime +
                ", fulfilmentTime: " + fulfilmentTime +
                ", isPayed: " + isPayed;
    }
}
