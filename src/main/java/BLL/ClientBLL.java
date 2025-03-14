package BLL;

import BLL.Validators.AgeValidator;
import BLL.Validators.EmailValidator;
import DAO.ClientDAO;
import Model.Client;

/**
 * The concrete implementation of the {@link BLL.AbstractBLL} class for the {@link Model.Client} type
 */
public class ClientBLL extends AbstractBLL<Client> {
    /**
     * The constructor of ClientBLL type instances
     * It requires a corresponding {@link DAO.ClientDAO} instance, as well as its corresponding validators, {@link BLL.Validators.AgeValidator}, and {@link  BLL.Validators.EmailValidator}
     */
    public ClientBLL() {
        super(new ClientDAO());
        addValidator(new AgeValidator());
        addValidator(new EmailValidator());
    }
}
