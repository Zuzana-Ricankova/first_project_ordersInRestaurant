package restaurant;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Iterator;


public class Dish{
    private Integer ID;
    private String title;
    private BigDecimal price;
    private String imageName;
    private Duration preparationTime;


    public Dish(Integer ID, String title, BigDecimal price, Duration preparationTime, String imageName) throws OrderException {
        this.ID = ID;
        this.title = title;
        this.price = price;
        setPreparationTime(preparationTime);
        this.imageName = imageName;
    }

    public Dish(Integer ID, String title, BigDecimal price, Duration preparationTime) throws OrderException {
        this.ID = ID;
        this.title = title;
        this.price = price;
        this.setPreparationTime(preparationTime);
        this.imageName = "blank";
    }

    public void removeDish(){

    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Duration getPreparationTime() {
        return preparationTime;
    }

    public void setPreparationTime(Duration preparationTime) throws OrderException {
        if (preparationTime.toMinutes() <= 0) {
            throw new OrderException("Wrong preparation time: " + preparationTime + "!" + " It cannot be null or negative!");
        }
        this.preparationTime = preparationTime;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    @Override
    public String toString() {
        return  ID.toString();
    }
}
