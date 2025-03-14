package BLL.Validators;

/**
 * The interface which is going to validate entries of the specified type
 *
 * @param <T> The type of entity to be validated by distinct criteria
 */
public interface Validator<T> {
    /**
     * The method which validates entries
     *
     * @param t The entity to be validated
     */
    void validate(T t);
}
