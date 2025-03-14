package Presentation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * The pane which handles the real time visualizations of operations on the Log table of {@link Model.Bill} objects.
 */
public class BillPane extends JPanel {
    /**
     * The Log table itself.
     */
    private JTable table;
    /**
     * The Log table's model.
     */
    private DefaultTableModel tableModel;

    /**
     * The constructor for the pane itself.
     */
    public BillPane() {
        table = new JTable();
        initTable();
    }

    /**
     * An initializer for the Log table.
     */
    public void initTable() {
        Vector<String> fields = new Vector<>();
        for (Field f : BillPane.class.getDeclaredFields()) {
            fields.add(f.getName());
        }
        tableModel = new DefaultTableModel(fields, 50);
        table = new JTable(tableModel);
    }
}
