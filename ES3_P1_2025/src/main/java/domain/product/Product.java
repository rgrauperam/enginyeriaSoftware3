package domain.product;

abstract class Product {
    protected String name;
    protected double standardPrice;
    protected String category;

    public Product(String name, double standardPrice, String category) {
        this.name = name;
        this.standardPrice = standardPrice;
        this.category = category;
    }

    public abstract double getFinalPrice();

    public void displayInfo() {
        System.out.println("Product: " + name + ", Category: " + category + ", Standard Price: " + standardPrice);
    }
}
