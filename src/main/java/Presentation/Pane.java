package Presentation;

import BLL.AbstractBLL;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.util.*;
import java.util.List;

/**
 * This class depicts the abstract description of operations for its heirs: {@link Presentation.ClientPane}, {@link Presentation.ProductPane}, and {@link Presentation.OrdersPane}.
 * It also describes the general layout that each Split Pane is supposed to respect.
 * It is composed of its left side: Text Fields for insertion, modification, and deletion, as well as buttons which confirm the said operations, and its right side: the Table we want to operate on, and a refresh button to visually update the data.
 * @param <T> The type of entity handled by the Pane class.
 */
public class Pane<T> extends JSplitPane implements ActionListener {
    /**
     * The Table on the right hand side of the Pane.
     */
    private JTable t;
    /**
     * The general model that the table should respect.
     */
    private DefaultTableModel model;
    /**
     * An Abstract Business Logic Layer which handles all the operations.
     */
    private AbstractBLL<T> abll;
    /**
     * The component left pane and right pane.
     */
    private JPanel leftPane, rightPane;
    /**
     * The aforementioned buttons.
     */
    private JButton insertButton, updateButton, deleteButton, refreshButton;
    /**
     * The concrete type of the parameterized type T.
     */
    private Class<T> type;
    /**
     * Maps describing the text fields for each of the insertion / modification sections.
     */
    private Map<String, JTextField> insert_textFields, update_textFields;
    /**
     * The text field associated with deletion. Deletion will be performed by IDs.
     */
    private JTextField deleteTextField;

    /**
     * The constructor for the abstract Pane object. It is responsible for initializing all the aforementioned variables.
     * @param abll The associated Abstract Business Logic Layer object.
     */

    @SuppressWarnings("unchecked")
    public Pane(AbstractBLL<T> abll) {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        leftPane = new JPanel();
        rightPane = new JPanel();
        initTable();
        leftPane.setLayout(new BoxLayout(leftPane, BoxLayout.Y_AXIS));
        rightPane.setLayout(new BoxLayout(rightPane, BoxLayout.Y_AXIS));
        model = (DefaultTableModel) t.getModel();
        insertButton = new JButton("Insert");
        updateButton = new JButton("Update");
        deleteButton = new JButton("Delete");
        refreshButton = new JButton("Refresh");
        insertButton.setFont(new Font("Comic Sans MS", Font.ITALIC, 12));
        updateButton.setFont(new Font("Comic Sans MS", Font.ITALIC, 12));
        deleteButton.setFont(new Font("Comic Sans MS", Font.ITALIC, 12));
        refreshButton.setFont(new Font("Comic Sans MS", Font.ITALIC, 12));
        refreshButton.addActionListener(this);
        setLeftComponent(leftPane);
        rightPane.add(new JScrollPane(t));
        rightPane.add(refreshButton);
        setRightComponent(rightPane);
        this.abll = abll;
        initTableInsert();
        initTableUpdate();
        initTableDelete();
        refreshTable();
    }

    /**
     * An initialization method for the insertion section within the left half of the pane.
     */
    public void initTableInsert() {
        JLabel insLabel = new JLabel("Insert new " + type.getSimpleName());
        insLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        leftPane.add(insLabel);
        insert_textFields = new HashMap<>();
        for (Field f : type.getDeclaredFields()) {
            JLabel label = new JLabel(f.getName());
            label.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
            leftPane.add(label);
            JTextField textField = new JTextField(10);
            insert_textFields.put(f.getName(), textField);
            leftPane.add(textField);
        }
        insertButton.addActionListener(this);
        leftPane.add(insertButton);
    }

    /**
     * An initialization method for the modification section within the left half of the pane.
     */
    public void initTableUpdate() {
        JLabel updLabel = new JLabel("Update " + type.getSimpleName());
        updLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        leftPane.add(updLabel);
        update_textFields = new HashMap<>();
        for (Field f : type.getDeclaredFields()) {
            JLabel label = new JLabel(f.getName());
            label.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
            leftPane.add(label);
            JTextField textField = new JTextField(10);
            update_textFields.put(f.getName(), textField);
            leftPane.add(textField);
        }
        updateButton.addActionListener(this);
        leftPane.add(updateButton);
    }
    /**
     * An initialization method for the deletion section within the left half of the pane.
     */
    public void initTableDelete() {
        JLabel delLabel = new JLabel("Delete " + type.getSimpleName());
        delLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        leftPane.add(delLabel);
        JLabel idLabel = new JLabel("id");
        idLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
        leftPane.add(idLabel);
        deleteTextField = new JTextField(20);
        leftPane.add(deleteTextField);
        deleteButton.addActionListener(this);
        leftPane.add(deleteButton);
    }
    /**
     * An initialization method for the table on the right half of the pane, which the operations will be performed on.
     */
    public void initTable() {
        Vector<String> fields = new Vector<>();
        for (Field f : type.getDeclaredFields()) {
            fields.add(f.getName());
        }
        DefaultTableModel tableModel = new DefaultTableModel(fields, 30);
        t = new JTable(tableModel);
    }

    /**
     * The refresh method for visually updating data at will.
     */
    public void refreshTable() {
        model.setRowCount(0);
        for (T t : abll.findAll()) {
            List<Object> lineData = new ArrayList<>();
            for (Field f : t.getClass().getDeclaredFields()) {
                try {
                    String fieldName = f.getName();
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                    Method method = propertyDescriptor.getReadMethod();
                    lineData.add(method.invoke(t));
                } catch (IntrospectionException | InvocationTargetException | IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
            model.addRow(lineData.toArray());
            System.out.println(lineData);
        }
    }

    /**
     * The method which gives life to the buttons.
     * @param e the event to be processed
     */
    public void actionPerformed(ActionEvent e) {
        T t;
        if (e.getSource().equals(insertButton)) {
//            for(JTextField tf : insert_textFields.values()){
//                System.out.println(tf.getText());
//            }
            t = createObject(insert_textFields);
            System.out.println(t);
            abll.insert(t);
            for (JTextField field : insert_textFields.values())
                field.setText("");
        }
        if (e.getSource().equals(updateButton)) {
            t = createObject(update_textFields);
            System.out.println(t.toString());
            abll.update(t);
            for (JTextField field : update_textFields.values())
                field.setText("");
        }
        if (e.getSource().equals(deleteButton)) {
            t = abll.findById(Integer.parseInt(deleteTextField.getText()));
            abll.delete(t);
            deleteTextField.setText("");
        }
        if (e.getSource().equals(refreshButton)) {
            refreshTable();
            for (JTextField field : insert_textFields.values())
                field.setText("");
            for (JTextField field : update_textFields.values())
                field.setText("");
            deleteTextField.setText("");
        }
        refreshTable();
    }

    /**
     * A very clever approach to creating an instance of an object, without being aware of its concrete type.
     * @see DAO.AbstractDAO for the original implementation of this method
     * @param textFields the text fields which the data for instantiating a new T type object will be extracted from
     * @return a concrete T instance, regardless of what T is
     */
    protected T createObject(Map<String, JTextField> textFields) {
        Constructor[] ctors = type.getDeclaredConstructors();
        Constructor ctor = null;
        for (int i = 0; i < ctors.length; i++) {
            ctor = ctors[i];
            if (ctor.getGenericParameterTypes().length == 0)
                break;
        }
        try {
            ctor.setAccessible(true);
            T object = (T) ctor.newInstance();
            for (Field f : type.getDeclaredFields()) {
                String fieldName = f.getName();
                for (Map.Entry<String, JTextField> entry : textFields.entrySet()) {
                    if (entry.getKey().equalsIgnoreCase(fieldName)) {
                        Constructor<?> constructor = f.getType().getDeclaredConstructor(String.class);
                        constructor.setAccessible(true);
                        Object obj;
                        try {
                            System.out.println(entry.getValue().getText());
                            obj = constructor.newInstance(entry.getValue().getText());
                            PropertyDescriptor pd = new PropertyDescriptor(f.getName(), type);
                            Method setter = pd.getWriteMethod();
                            setter.invoke(object, obj);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                }
            }
            return object;
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
    }
}
