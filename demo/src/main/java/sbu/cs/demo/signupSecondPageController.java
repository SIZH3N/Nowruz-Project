package sbu.cs.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sbu.cs.demo.openpage.OpenPage;
import sbu.cs.demo.scene.entrance.Account;
import sbu.cs.demo.scene.entrance.AccountFactory;
import sbu.cs.demo.scene.entrance.AccountManager;

import java.io.IOException;

public class signupSecondPageController {

    @FXML
    public ComboBox roleSelector;
    @FXML
    public PasswordField passwordField;
    @FXML
    public TextField usernameField;

    @FXML
    private String email;
    @FXML
    private int age;
    @FXML
    private String name;

    @FXML
    public void handleSignUp(ActionEvent event) throws IOException {

        String username = usernameField.getText();
        String password = passwordField.getText();
        if (roleSelector.getValue() == null) {
            System.out.println("Please select a role.");
            return;
        }
        String role = roleSelector.getValue().toString();


        System.out.println(role);

        Account account = AccountFactory.createAccount(name, age, email, username, password, role);
        if (account == null) {
            System.out.println("Account creation failed");
        } else {
            System.out.println("Account created successfully");


            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("mainpage.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(true);
            stage.show();
        }
    }

    public void setVars(String email, int age, String name) {
        this.email = email;
        this.age = age;
        this.name = name;
    }

    @FXML
    public void backPage(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("signup.fxml"));
        Parent root = loader.load();
        Signup page = loader.getController();

        page.emailField.setText(email);
        page.ageField.setText(Integer.toString(age));
        page.nameField.setText(name);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();

    }
}
