package logic;

public class User extends BaseEntity{
    private String name;
    private String mail;
    private Role role;
    private Account account;

    public User(long id, String name, String mail, Role role, Account account) {
        super(id);

    }
}
