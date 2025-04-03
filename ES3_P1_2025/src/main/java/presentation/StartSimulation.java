package presentation;

import domain.order.Order;
import domain.payment.PaymentMethod;
import domain.product.FixedDiscountProduct;
import domain.product.NoDiscountProduct;
import domain.product.PercentageDiscountProduct;
import domain.product.Product;

import domain.payment.Payment;

import domain.store.Store;

import java.util.LinkedList;
import java.util.List;

public class StartSimulation {

    public static void main (String [] args) {

        Store store = Store.getInstance(); //TODO: Create new store

        System.out.println("Welcome to " + store.getName() + " at " + store.getAddress());
        System.out.println("*******************");


        List<Product> productList = addProducts();
        show(productList);
        Order o = createOrder(productList);
        System.out.println("Pending order info:");
        System.out.println("--------");
        showOrder(o);
        System.out.println("-----------------------------------------");
        System.out.println("Order payment:");
        System.out.println("--------");
        //TODO: You can use these lines below to create different payment methods
        PaymentMethod paymentMethod = PaymentMethod.CreditCard;
        PaymentMethod paymentMethod2 = PaymentMethod.Bizum;
        PaymentMethod paymentMethod3 = PaymentMethod.PayPal;


        //TODO: Complete to create Payment
        //Mètode de pagament amb creditCard
        Payment pay = paymentMethod.createPayment();

        o.confirmOrderAndPay(pay);
        System.out.println("Completed order info:");
        System.out.println("--------");
        showCompletedOrder(o);
        System.out.println("-----------------------------------------");
    }



    private static List<Product> addProducts() {
        List<Product> prodList = new LinkedList<>();

        /*TODO: Create 10 different products with different discount types and add them to prodList*/

        prodList.add(new Product("Product1", 100.0, "Category1", new NoDiscountProduct());
        prodList.add(new Product("Product2", 200.0, "Category2", new FixedDiscountProduct(20.0)));
        prodList.add(new Product("Product3", 300.0, "Category3", new PercentageDiscountProduct(10.0)));
        prodList.add(new Product("Product4", 400.0, "Category4", new NoDiscountProduct()));
        prodList.add(new Product("Product5", 500.0, "Category5", new FixedDiscountProduct(50.0)));
        prodList.add(new Product("Product6", 600.0, "Category6", new PercentageDiscountProduct(15.0)));
        prodList.add(new Product("Product7", 700.0, "Category7", new NoDiscountProduct()));
        prodList.add(new Product("Product8", 800.0, "Category8", new FixedDiscountProduct(80.0)));
        prodList.add(new Product("Product9", 900.0, "Category9", new PercentageDiscountProduct(20.0)));
        prodList.add(new Product("Product10", 1000.0, "Category10", new NoDiscountProduct()));

        /*TODO: Change the discount type of one of those created products*/
        prodList.get(0).setDiscountStrategy(new PercentageDiscountProduct(5.0));

        return prodList;
    }

    private static void show(List<Product> prodList){
        //System.out.println();
        System.out.println("Our store offers the following products:");
        for(Product p: prodList){
            show(p);
        }
        System.out.println("-----------------------------------------");

    }

    private static void show(Product p){
        System.out.println("Name:" + p.getName());
        System.out.println("Category:" + p.getCategory());
        System.out.println("Price:" + p.getStandardPrice());
        if (p.getStandardPrice() != p.getDiscountedPrice()) {
            System.out.println("Discounted Price: " + p.getDiscountedPrice());
        }
        System.out.println("******");
    }

    private static Order createOrder(List<Product> prodList) {
        /*TODO: Create a new pending order and add three different products from prodList*/
        Order o = new Order();
        o.addProduct(prodList.get(0));
        o.addProduct(prodList.get(1));
        o.addProduct(prodList.get(2));
        //TODO: Complete
        return o;
    }

    private static void showOrder(Order o) {
        System.out.println("Order ID: #" + o.getId());
        System.out.println("Total price w/discount: " + o.getTotal());
        System.out.println("Total price w/o discount: " + o.getTotalWithoutDiscount());
    }

    private static void showCompletedOrder(Order o) {
        showOrder(o);
        System.out.println("Start date and time: " + o.getPaymentStartDate());
        System.out.println("End date and time: " + o.getPaymentEndDate());
        System.out.println("Congratulations for buying the following products: ");
        System.out.println(o.getProductNames());
    }
}
