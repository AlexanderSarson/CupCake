package logic;

/**
 * This class represents a user of the system, with different types of roles as well as different user-related data.
 * @author Benjamin Paepke
 * @version 1.0
 */
public class User extends BaseEntity{
    private String name;
    private String mail;
    private Role role;
    private Account account;

    /**
     * The basic constructor of the user.
     * @param id The id of the user.
     * @param name The full name of the user.
     * @param mail The e-mail address of the user.
     * @param role The role of the user, {@link logic.Role}
     * @param account The account object of the user, {@link logic.Account}
     */
    public User(long id, String name, String mail, Role role, Account account) {
        super(id);
        this.name = name;
        this.mail = mail;
        this.role = role;
        this.account = account;
    }

    /**
     * Returns the id of the given user.
     * @return long-valued id of the user.
     */
    public long getID() { return id;}

    /**
     * Sets an id for the given user.
     * @param id The new id of the user.
     */
    public void setID(long id) { this.id = id; }

    /**
     * Gets the name of the user.
     * @return String representing the name of the user.
     */
    public String getName() { return name;}

    /**
     * Sets the name of the given user.
     * @param name The new name of the user.
     */
    public void setName(String name) {this.name = name;}

    /**
     * Gets the mail as a string, of the given user.
     * @return A string representing the user's mail.
     */
    public String getMail() {return mail;}

    /**
     * Sets a new mail address of the given user.
     * @param mail The new mail address for the user.
     */
    public void setMail(String mail) {this.mail = mail;}

    /**
     * Gets the role of the given user.
     * @return A {@link logic.Role} of the given user.
     */
    public Role getRole() {return role;}

    /**
     * Sets the {@link logic.Role} of the user.
     * @param role the new role of the user.
     */
    public void setRole(Role role) {this.role = role;}

    /**
     * Gets the account associated with the user.
     * @return
     */
    public Account getAccount() { return account;}

    /**
     * Sets the new account of the given user.
     * @param account The new account of the user.
     */
    public void setAccount(Account account) {this.account = account;}
}
