package BLL;

import BLL.Validators.ProductQuantityValidator;
import DAO.ProductDAO;
import Model.Product;
/**
 * The concrete implementation of the {@link BLL.AbstractBLL} class for the {@link Model.Product} type
 */
public class ProductBLL extends AbstractBLL<Product> {
    /**
     * The constructor of ProductBLL type instances
     * It requires a corresponding {@link DAO.ProductDAO} instance, as well as its corresponding validator, {@link BLL.Validators.ProductQuantityValidator}
     */
    public ProductBLL() {
        super(new ProductDAO());
        addValidator(new ProductQuantityValidator());
    }
}
