package restaurant;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Order implements Comparable<Order>{

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
        setOrderedTime(orderedTime);
        setFulfilmentTime(fulfilmentTime);
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

    public void setOrderedTime(LocalDateTime time) throws OrderException {
        if (time == null) {
            throw new OrderException("Ordered time cannot be null.");
        }

        int seconds = time.getSecond();
        if (seconds != 0) {
            throw new OrderException("Ordered time cannot contain seconds. If you used LocalDateTime.now() than edit the format on: LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES).withSecond(0).withNano(0)");
        }

        int nano = time.getNano();
        if (nano != 0) {
            throw new OrderException("Ordered time cannot contain nanosecond.");
        }

        this.orderedTime = time.withSecond(0).withNano(0);

    }


    public LocalDateTime getFulfilmentTime() {
        return fulfilmentTime;
    }

    public void setFulfilmentTime(LocalDateTime time) throws OrderException {
        if (time == null) {
            this.fulfilmentTime = null;
            return;
        }

        int seconds = time.getSecond();
        if (seconds != 0) {
            throw new OrderException("Fulfilment time cannot contain seconds! If you used LocalDateTime.now() than edit the format on: LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES).withSecond(0).withNano(0).");
        }

        int nano = time.getNano();
        if (nano != 0) {
            throw new OrderException("Fulfilment time cannot contain nanosecond.");
        }

        this.fulfilmentTime = time.withSecond(0).withNano(0);
    }

    public Boolean getPayed() {
        return isPayed;
    }

    public void setPayed(Boolean payed) {
        isPayed = payed;
    }

    @Override
    public int compareTo(Order order1) {
        return this.orderedTime.compareTo(order1.getOrderedTime());
    }

    public String toStringWithTableInfo() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        String formattedOrderedTime = orderedTime.format(formatter);
        String formattedFulfilmentTime = null;

        if (fulfilmentTime != null) {
        formattedFulfilmentTime = fulfilmentTime.format(formatter);
    }

         return  chooseDish.getTitle() +
            " " + quantity + "x (" + chooseDish.getPrice() + " Kč):" +"\t" +
            formattedOrderedTime + "-" +
            ((formattedFulfilmentTime != null) ? formattedFulfilmentTime : "") +
            " " + (isPayed ? "Zaplaceno" : "");
}

    @Override
    public String toString() {
        return "table: " + table +
                ", quantity: " + quantity +
                ", chooseDish: " + chooseDish +
                ", orderedTime: " + orderedTime +
                ", fulfilmentTime: " + fulfilmentTime +
                ", isPayed: " + isPayed;
    }
}
