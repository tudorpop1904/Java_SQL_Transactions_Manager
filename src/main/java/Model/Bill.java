package Model;

/**
 * This record defines an immutable class, used to store orders which were inserted / updated.
 * Bills can only be inserted, they may not be modified or deleted.
 * In other words, the bill table acts as a log table.
 * @param order the order ID.
 * @param client the client ID.
 * @param product the product ID.
 * @param quantity the ordered quantity.
 */
public record Bill(int order, int client, int product, int quantity) {}
