package BLL;

import BLL.Validators.OrderQuantityValidator;
import DAO.OrdersDAO;
import Model.Orders;
import Model.Product;
/**
 * The concrete implementation of the {@link BLL.AbstractBLL} class for the {@link Model.Orders} type
 */
public class OrdersBLL extends AbstractBLL<Orders>{
    /**
     * The corresponding {@link BLL.ProductBLL} type instance associated with the OrdersBLL object
     * Every time an order is placed, the remaining quantity of the desired product will decrease
     * This decreasing action is handled by the Business Logic Layer of the {@link Model.Product} type
     */
    private final ProductBLL productBLL;
    /**
     * The constructor of OrdersBLL type instances
     * It requires a corresponding {@link DAO.OrdersDAO} instance, its validator, {@link BLL.Validators.OrderQuantityValidator}, and the associated {@link BLL.ProductBLL} instance
     */
    public OrdersBLL() {
        super(new OrdersDAO());
        addValidator(new OrderQuantityValidator());
        productBLL = new ProductBLL();
    }

    /**
     * As mentioned above, the {@link Model.Orders} type objects interact differently with the database.
     * Aside from updating its own table, when an order is inserted or updated, the remaining quantity of the ordered object will decrease.
     * Due to this distinct interaction with the database, the insertion, and modification methods for objects needs to be handled differently.
     * At first, we will discuss the modified version of the "insert" method. Upon inserting a new order, the quantity of the product will decrease by the ordered amount.
     *
     * @param order The entity to insert.
     */
    @Override
    public void insert(Orders order) {
        checkValidators(order);
        Product p = productBLL.findById(order.getProductID());
        if (order.getQuantity() > p.getQuantity())
            throw new IllegalArgumentException("Not enough items in stock!");
        p.setQuantity(p.getQuantity() - order.getQuantity());
        productBLL.update(p);
        super.insert(order);
    }

    /**
     * Similarly, the "update" method is also overridden here, with the added functionality that also updates the products table accordingly.
     *
     * @param order The entity to update.
     */
    @Override
    public void update(Orders order) {
        checkValidators(order);
        super.update(order);
        Product newProduct = productBLL.findById(order.getProductID());
        newProduct.setQuantity(newProduct.getQuantity() - order.getQuantity());
        productBLL.update(newProduct);
    }
}
