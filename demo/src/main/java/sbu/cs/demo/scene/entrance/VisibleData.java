package sbu.cs.demo.scene.entrance;

public class VisibleData {
    private static VisibleData instance = new VisibleData();
    private  String username;
    private  String role;

    private VisibleData() {}

    public static VisibleData getInstance() {
        return instance;
    }
    public String getUsername() {
        return username;
    }
    public String getRole() {
        return role;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setRole(String role) {
        this.role = role;
    }

}
