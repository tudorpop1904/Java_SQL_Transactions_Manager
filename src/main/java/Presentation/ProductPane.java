package Presentation;

import BLL.ProductBLL;
import Model.Product;

import javax.swing.*;
/**
 * The concrete implementation of {@link Presentation.Pane} for the {@link Model.Product} type
 */
public class ProductPane extends Pane<Product> {
    public ProductPane() {
        super(new ProductBLL());
    }
}
