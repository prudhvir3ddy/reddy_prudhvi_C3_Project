import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private final String name;
    private final String location;
    private final LocalTime openingTime;
    private final LocalTime closingTime;
    private final List<Item> menu = new ArrayList<Item>();

    public Restaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        this.name = name;
        this.location = location;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }

    public boolean isRestaurantOpen() {
        //DELETE ABOVE STATEMENT AND WRITE CODE HERE
        LocalTime currentTime = getCurrentTime();
        return currentTime.isAfter(openingTime) && currentTime.isBefore(closingTime);
    }

    public LocalTime getCurrentTime() {
        return LocalTime.now();
    }

    public List<Item> getMenu() {
        //DELETE ABOVE RETURN STATEMENT AND WRITE CODE HERE
        return menu;
    }

    private Item findItemByName(String itemName) {
        for (Item item : menu) {
            if (item.getName().equals(itemName))
                return item;
        }
        return null;
    }

    public void addToMenu(String name, int price) {
        Item newItem = new Item(name, price);
        menu.add(newItem);
    }

    public void removeFromMenu(String itemName) throws itemNotFoundException {

        Item itemToBeRemoved = findItemByName(itemName);
        if (itemToBeRemoved == null)
            throw new itemNotFoundException(itemName);

        menu.remove(itemToBeRemoved);
    }

    public void displayDetails() {
        System.out.println("Restaurant:" + name + "\n"
                + "Location:" + location + "\n"
                + "Opening time:" + openingTime + "\n"
                + "Closing time:" + closingTime + "\n"
                + "Menu:" + "\n" + getMenu());

    }

    public String getName() {
        return name;
    }

    public int totalPriceForSelectedItems(List<String> itemNames) {
        int totalPrice = 0;
        for (String itemName : itemNames) {
            Item menuItem = findItemByName(itemName);
            if (menuItem != null) {
                totalPrice += menuItem.getPrice();
            }
        }
        return totalPrice;
    }
}
