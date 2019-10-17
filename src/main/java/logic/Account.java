package logic;

/**
 * The account class represents a customer's account, containing details about balance.
 * @author Benjamin Paepke
 * @version 1.0
 */
public class Account extends BaseEntity{
    private int balance;

    /**
     * Constructor of an Account, without an initial balance
     * @param id The id of the account.
     */
    public Account(long id) {
        super(id);
    }

    /**
     * Constructor of an account, with an initial balance.
     * @param id The id of the account
     * @param balance The balance of the account.
     */
    public Account(long id, int balance) {
        super(id);
        this.balance = balance;
    }

    /**
     * Gets the balance of the given account.
     * @return The current balance.
     */
    public int getBalance() { return balance;}

    /**
     * Sets the new balance of the account.
     * @param balance The new balance.
     */
    public void setBalance(int balance) {this.balance = balance;}
}
