package Model;
/**
 * Represents a client entity with basic information such as ID, name, address, E-Mail, and age.
 */
public class Client {
    private Integer id, age;
    private String name, address, email;
    /**
     * Constructs a new Client object with the specified parameters.
     *
     * @param id      The unique identifier of the client.
     * @param age     The age of the client.
     * @param name    The name of the client.
     * @param address The address of the client.
     * @param email   The E-Mail address of the client.
     */
    public Client(int id, int age, String name, String address, String email) {
        this.id = id;
        this.age = age;
        this.name = name;
        this.address = address;
        this.email = email;
    }
    /**
     * Default constructor for Client class.
     */
    public Client(){}

    public void setId(int id) {
        this.id = id;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }
    @Override
    public String toString() {
        return "Client:\nID: " + id + "\nName: " + name + "\nAddress: " + address + "\nE-Mail: " + email + "\nAge: " + age + "\n";
    }
}
