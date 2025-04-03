package domain.store;

public class Store {
    private static Store instance;
    private String name;
    private String address;
    private int totalOrders;

    private Store() {
        this.name = "MyStore";
        this.address = "chancletas.com";
        this.totalOrders = 0;
    }

    // SINGLETON
    public static Store getInstance() {
        if (instance == null) {
            instance = new Store();
        }
        return instance;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public int getTotalOrders() {
        return totalOrders;
    }

    public synchronized int nextAvailableOrderNumber() {
        return totalOrders++;
    }
}