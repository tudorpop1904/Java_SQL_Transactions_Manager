package DAO;

import BLL.OrdersBLL;
import Model.Bill;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import Connection.ConnectionFactory;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The class representing the data access objects for the immutable type {@link Model.Bill}.
 */
public class BillDAO {
    /**
     * The associated business logic layer for orders
     */
    private final OrdersBLL ordersBLL;
    private static final Logger LOGGER = Logger.getLogger(Bill.class.getName());
    public BillDAO() {
        ordersBLL = new OrdersBLL();
    }

    /**
     * A method which extracts the values of the bill instance.
     *
     * @param bill The bill object to extract the values from.
     * @return The values.
     */
    private Map<String,String> getValues(Bill bill){
        Map<String, String> result = new HashMap<>();
        try {
            for(Field f: Bill.class.getDeclaredFields()) {
                String fieldName = f.getName();
                PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, Bill.class);
                Method method = propertyDescriptor.getReadMethod(); // age -> getAge
                if(f.getType() == String.class){
                    result.put(fieldName, "'" + method.invoke(bill).toString() + "'");
                }else {
                    result.put(fieldName, method.invoke(bill).toString());
                }
            }
        } catch (IntrospectionException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * The method for inserting a bill into the Log table.
     * @param bill The bill to be inserted.
     */
    public void insert(Bill bill) {
        Map<String, String> fields = getValues(bill);
        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO Log (");
        for (String key : fields.keySet()){
            query.append(key).append(", ");
        }
        query.deleteCharAt(query.length()-1);
        query.deleteCharAt(query.length()-1);
        query.append(") ");
        query.append("VALUES (");
        for (String value : fields.values()){
            query.append(value).append(", ");
        }
        query.deleteCharAt(query.length()-1);
        query.deleteCharAt(query.length()-1);
        query.append(");");
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query.toString());
            statement.execute();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, Bill.class.getName() + "DAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }
}
