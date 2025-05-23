package domain.product;

public abstract class Product {
    protected String name;
    protected double standardPrice;
    protected String category;
    protected DiscountStrategy discountStrategy;

    public Product(String name, double standardPrice, String category) {
        this.name = name;
        this.standardPrice = standardPrice;
        this.category = category;
        this.discountStrategy = new NoDiscountStrategy();
    }

    public Product(String name, double standardPrice, String category, DiscountStrategy discountStrategy) {
        this.name = name;
        this.standardPrice = standardPrice;
        this.category = category;
        this.discountStrategy = discountStrategy;
    }

    public String getName() {
        return name;
    }

    public double getStandardPrice() {
        return standardPrice;
    }

    public String getCategory() {
        return category;
    }

    public DiscountStrategy getDiscountStrategy() {
        return discountStrategy;
    }

    public void setDiscountStrategy(DiscountStrategy discountStrategy) {
        this.discountStrategy = discountStrategy;
    }

    public double getDiscountedPrice() {
        return discountStrategy.applyDiscount(standardPrice);
    }

    public void displayInfo() {
        System.out.println("Product: " + name + ", Category: " + category + ", Standard Price: " + standardPrice);
    }
}