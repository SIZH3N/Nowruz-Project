package sbu.cs.demo.scene.entrance;

public class AccountFactory {

    public static Account account = null;

    public static Account createAccount(String name, int age, String email, String username, String password, String role) {
//        System.out.println("Username: " + username + ", Password: " + password + ", Email: " + email + ", Age: " + age + ", Name: " + name + ", Role: " + role);
        if (AccountManager.UsernameTaken(username)) {
            System.out.println("Username already exists");
        } else {
            Account account ;
            switch (role) {
                case "User": {
                    account = new User(name, age, email, username, password, role,null );
                    break;
                }
                case "Artist": {
                    account = new Artist(name, age, email, username, password, role,null);
                    AccountManager.addAccount(account, true);
                    return account;
                }
                case "Admin": {
                    account = new Admin(name, age, email, username, password, role,null);
                    break;
                }
                default:
                    throw new IllegalArgumentException("Invalid account type");
            }

            AccountManager.addAccount(account);
            return account;
        }
        return null;
    }
}
