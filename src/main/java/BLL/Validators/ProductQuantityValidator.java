package BLL.Validators;

import Model.Product;

/**
 * The concrete implementation of the {@link BLL.Validators.Validator} interface for the {@link Model.Product} type, it handles product quantity validation
 */
public class ProductQuantityValidator implements Validator<Product> {
    /**
     * The method which validates the product quantity
     *
     * @param t The entity to be validated
     * @throws IllegalArgumentException If the desired product for entry/update has a negative quantity
     */
    public void validate(Product t) {
        if (t.getQuantity() < 0)
            throw new IllegalArgumentException("How can you have a negative amount of " + t.getName() + "s?");
    }
}
