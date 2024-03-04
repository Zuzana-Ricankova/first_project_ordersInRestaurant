import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Dish {

    private String title;
    private BigDecimal price;
//    private LocalDateTime preparationTime; //= preparationTime > 0 ;
    private String imageName;
    private Duration preparationTime;



    public Dish(String title, BigDecimal price, Duration preparationTime, String imageName) throws OrdersException{
        this.title = title;
        this.price = price;
        setPreparationTime(preparationTime);
        this.imageName = imageName;
    }

    public Dish(String title, BigDecimal price, Duration preparationTime) throws OrdersException{
        this.title = title;
        this.price = price;
        setPreparationTime(preparationTime);
        this.imageName = "blank";
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

    public void setPreparationTime(Duration preparationTime) throws OrdersException{
        if (preparationTime.toMinutes() <= 0) {
            throw new OrdersException("Wrong preparation time: " + preparationTime + "!" + " It cannot be null or negative!");
        }
        this.preparationTime = preparationTime;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }



}
