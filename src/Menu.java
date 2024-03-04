import java.math.BigDecimal;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Menu extends Dish{
    private static int nextID = 0; // Statická proměnná pro ukládání dalšího ID
    private int ID;
    protected List <Dish> menu = new ArrayList<>();



    public Menu(String title, BigDecimal price, Duration preparationTime, String imageName, int ID, List<Dish> menu) throws OrdersException {
        super(title, price, preparationTime, imageName);
        this.ID = ++nextID;
        this.menu = menu;
    }

    public Menu(String title, BigDecimal price, Duration preparationTime, int ID, List<Dish> menu) throws OrdersException {
        super(title, price, preparationTime);
        this.ID = ++nextID;
        this.menu = menu;
    }
}

