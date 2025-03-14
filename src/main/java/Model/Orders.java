package Model;
/**
 * Represents an order entity with information about the order ID, client ID, product ID, and quantity.
 */
public class Orders {
    private Integer id, clientID, productID, quantity;
    /**
     * Constructs a new Orders object with the specified parameters.
     *
     * @param id        The unique identifier of the order.
     * @param clientID  The ID of the client associated with the order.
     * @param productID The ID of the product associated with the order.
     * @param quantity  The quantity of the product ordered.
     */
    public Orders(int id, int clientID, int productID, int quantity) {
        this.id = id;
        this.clientID = clientID;
        this.productID = productID;
        this.quantity = quantity;
    }
    /**
     * Default constructor for Orders class.
     */
    public Orders() {}

    public int getId() {
        return id;
    }

    public int getClientID() {
        return clientID;
    }

    public int getProductID() {
        return productID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    @Override
    public String toString() {
        return "Order\nID: " + id + "\nClient ID: " + clientID + "\nProduct ID: " + productID + "\nQuantity: " + quantity + "\n";
    }
}
