package BLL.Validators;
import Model.Client;

/**
 * The first concrete implementation of the {@link BLL.Validators.Validator} interface for the {@link Model.Client} type, it handles age validation
 *
 */
public class AgeValidator implements Validator<Client> {
    /**
     * The imposed lower age bound
     */
    private static final int MIN_AGE = 5;
    /**
     * The imposed upper age bound
     */
    private static final int MAX_AGE = 100;

    /**
     * This implementation of the {@link BLL.Validators.Validator} interface checks if a client's age lies in the allowed age range
     *
     * @param t The client to be validated
     * @throws IllegalArgumentException If the entrant is too young or too old
     */
    public void validate(Client t) {
        if (t.getAge() < MIN_AGE)
            throw new IllegalArgumentException("Customer is too young for this purchase!");
        if (t.getAge() > MAX_AGE)
            throw new IllegalArgumentException("Customer is too old for this purchase!");
    }
}
