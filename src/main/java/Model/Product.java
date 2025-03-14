package Model;
/**
 * Represents a product entity with basic information such as ID, name, and quantity.
 */
public class Product {
    private Integer id, quantity;
    private String name;
    /**
     * Constructs a new Product object with the specified parameters.
     *
     * @param id       The unique identifier of the product.
     * @param quantity The quantity of the product.
     * @param name     The name of the product.
     */
    public Product(int id, int quantity, String name) {
        this.id = id;
        this.quantity = quantity;
        this.name = name;
    }
    /**
     * Default constructor for Product class.
     */
    public Product() {}

    public int getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return "Product:\nID: " + id + "\nName: " + name + "\nQuantity: " + quantity + "\n";
    }
}
