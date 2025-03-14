package BLL.Validators;

import DAO.ProductDAO;
import Model.Orders;

/**
 * The concrete implementation of the {@link BLL.Validators.Validator} interface for the {@link Model.Orders} type, it handles order quantity validation
 */
public class OrderQuantityValidator implements Validator<Orders> {
    /**
     * The validation method for order quantities.
     *
     * @param t The entity to be validated
     * @throws IllegalArgumentException If the client is attempting to order either a non-positive amount of items, or more items than there are in stock
     */
    public void validate(Orders t) {
        ProductDAO dao = new ProductDAO();
        if (t.getQuantity() < 1)
            throw new IllegalArgumentException("What are you ordering exactly?");
        if (t.getQuantity() > dao.findById(t.getProductID()).getQuantity()) {
            throw new IllegalArgumentException("Not enough " + dao.findById(t.getProductID()).getName() + "s in stock!");
        }
    }
}
