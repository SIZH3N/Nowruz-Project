package sbu.cs.demo;

import javafx.scene.control.Label;

public class ProfileController {
    public Label usernameLabel;
    public Label passwordLabel;
    public Label nameLabel;
    public Label emailLabel;
    public Label ageLabel;
    public Label roleLabel;

    public void setData(String username, String password, String name, String email, String age, String role) {
        usernameLabel.setText(username);
        passwordLabel.setText(password);
        nameLabel.setText(name);
        emailLabel.setText(email);
        ageLabel.setText(age);
        roleLabel.setText(role);
    }
}
