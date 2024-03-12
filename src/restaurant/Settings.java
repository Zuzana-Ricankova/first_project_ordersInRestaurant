package restaurant;

public class Settings {
    private static final String FILENAMEDISHES = "menu.txt";
    private static final String FILENAMEORDERS = "orders.txt";

    public static String getFileNameDishes() {
        return FILENAMEDISHES;
    }
    public static String getFileNameOrders(){
        return FILENAMEORDERS;
    }
}
