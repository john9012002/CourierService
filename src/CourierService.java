import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CourierService {
    private List<Product> products;
    private List<Order> orders;

    public CourierService() {
        products = new ArrayList<>();
        orders = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void placeOrder(Order order) {
        orders.add(order);
    }

    public void returnProduct(Order order) {
        order.setReturned(true);
    }

    public void showProducts() {
        System.out.println("Available products:");
        for (Product product : products) {
            System.out.println(product);
        }
    }

    public void showOrders() {
        System.out.println("Orders:");
        for (Order order : orders) {
            System.out.println(order);
        }
    }

    public static void main(String[] args) {
        CourierService courierService = new CourierService();
        Scanner scanner = new Scanner(System.in);

        // Add some products
        courierService.addProduct(new Product("Book", 10.0));
        courierService.addProduct(new Product("Phone", 500.0));
        courierService.addProduct(new Product("Laptop", 1000.0));

        // Place an order
        System.out.println("Place an order:");
        courierService.showProducts();
        System.out.print("Enter product name: ");
        String productName = scanner.nextLine();
        System.out.print("Enter quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        System.out.print("Enter delivery address: ");
        String deliveryAddress = scanner.nextLine();
        Order order = new Order(productName, quantity, deliveryAddress);
        courierService.placeOrder(order);
        System.out.println("Order placed successfully!");

        // Return a product
        System.out.println("Return a product:");
        courierService.showOrders();
        System.out.print("Enter order ID: ");
        int orderId = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        Order returnedOrder = courierService.orders.get(orderId - 1);
        courierService.returnProduct(returnedOrder);
        System.out.println("Product returned successfully!");

        // Show orders
        courierService.showOrders();
    }
}

class Product {
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return name + " - $" + price;
    }
}

class Order {
    private static int nextId = 1;

    private int id;
    private String productName;
    private int quantity;
    private String deliveryAddress;
    private boolean returned;

    public Order(String productName, int quantity, String deliveryAddress) {
        this.id = nextId++;
        this.productName = productName;
        this.quantity = quantity;
        this.deliveryAddress = deliveryAddress;
        this.returned = false;
    }

    public int getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public boolean isReturned() {
        return returned;
    }

    public void setReturned(boolean returned) {
        this.returned = returned;
    }

    @Override
    public String toString() {
        return "Order #" + id + ": " + productName + " x " + quantity + " to " + deliveryAddress + " (returned: " + returned + ")";
    }
}