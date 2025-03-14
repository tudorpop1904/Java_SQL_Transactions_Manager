package BLL;

import BLL.Validators.Validator;
import DAO.AbstractDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
/**
 * Abstract Business Logic Layer class providing common operations for entities.
 *
 * @param <T> The type of entity handled by the BLL.
 */
public class AbstractBLL<T> {
    private final List<Validator<T>> validators;
    private final AbstractDAO<T> DAO;
    /**
     * Adds a validator to the list of validators.
     *
     * @param validator The validator to be added.
     */
    protected void addValidator(Validator<T> validator) {
        this.validators.add(validator);
    }
    /**
     * Constructs a new AbstractBLL object with the specified AbstractDAO.
     *
     * @param DAO The AbstractDAO instance associated with this BLL.
     */
    public AbstractBLL(AbstractDAO<T> DAO) {
        validators = new ArrayList<>();
        this.DAO = DAO;
    }
    /**
     * Checks all validators associated with the entity.
     *
     * @param t The entity to be validated.
     */
    protected void checkValidators(T t){
        for (Validator<T> validator : validators)
            validator.validate(t);
    }
    /**
     * Retrieves all entities of type T.
     *
     * @return A list of all entities of type T.
     */
    public List<T> findAll() {
        return DAO.findAll();
    }
    /**
     * Retrieves the entity with the specified ID.
     *
     * @param id The ID of the entity to retrieve.
     * @return The entity with the specified ID.
     * @throws NoSuchElementException If the entity with the specified ID is not found.
     */
    public T findById(int id) throws NoSuchElementException {
        T t = DAO.findById(id);
        if (t == null)
            throw new NoSuchElementException(DAO.getType().getName() + " with ID " + id + " not found!");
        return t;
    }
    /**
     * Inserts a new entity into the database.
     *
     * @param t The entity to insert.
     */
    public void insert(T t) {
        checkValidators(t);
        DAO.insert(t);
    }
    /**
     * Updates an existing entity in the database.
     *
     * @param t The entity to update.
     */
    public void update(T t) {
        checkValidators(t);
        DAO.update(t);
    }
    /**
     * Deletes an entity from the database.
     *
     * @param t The entity to delete.
     */
    public void delete(T t) {
        DAO.delete(t);
    }
}
