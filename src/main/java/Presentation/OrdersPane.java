package Presentation;

import BLL.OrdersBLL;
import Model.Orders;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * The concrete implementation of {@link Presentation.Pane} for the {@link Model.Orders} type
 */
public class OrdersPane extends Pane<Orders> {
    public OrdersPane() {
        super(new OrdersBLL());
    }
}
