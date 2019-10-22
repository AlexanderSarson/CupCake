package logic;

/**
 * The account class represents a customer's account, containing details about balance.
 * @author Benjamin Paepke
 * @version 1.0
 */
public class Account extends BaseEntity{
    private int balance;

    /**
     * Base constructor, if we don't have id.
     */
    public Account() { }

    /**
     * Constructor of an account, with an initial balance.
     * @param id The id of the account
     * @param balance The balance of the account.
     * @throws IllegalArgumentException if the balance is a negative value.
     */
    public Account(int id, int balance) throws IllegalArgumentException{
        super.id = id;
        if(validateBalance(balance)) {
            this.balance = balance;
        }else {
            throw new IllegalArgumentException("Balance must be a positive value");
        }
    }

    public Account(int balance) {
        if(validateBalance(balance)) {
            this.balance = balance;
        }else {
            throw new IllegalArgumentException("Balance must be a positive value");
        }
    }

    private boolean validateBalance(int balance) {
        return balance >= 0;
    }

    /**
     * Gets the balance of the given account.
     * @return The current balance.
     */
    public int getBalance() { return balance;}

    /**
     * Sets the new balance of the account.
     * @param balance The new balance.
     * @throws IllegalArgumentException if the balance is a non-positive value.
     */
    public void setBalance(int balance) throws IllegalArgumentException{
        if(validateBalance(balance)) {
            this.balance = balance;
        }else {
            throw new IllegalArgumentException("Balance must be a positive value");
        }
    }

    /**
     * Reduces the account balance by a set value.
     * @param reduction The amount to reduce the balance by.
     * @throws IllegalArgumentException if the amount to be reduced is a non-positive value.
     */
    public void reduceBalance(int reduction) throws IllegalArgumentException{
        if(reduction < 0) throw new IllegalArgumentException("Reduction must be non-zero value");
        balance -= reduction;
        if(balance < 0) balance = 0;
    }
}
