package sbu.cs.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sbu.cs.demo.scene.entrance.Account;
import sbu.cs.demo.scene.entrance.AccountManager;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Signup {
    @FXML
    public TextField emailField;
    @FXML
    public TextField ageField;
    @FXML
    public TextField nameField;


    @FXML
    public void nextPage(ActionEvent event) throws IOException {
        String email=null;
        int age=0;
        String name=null;
        if (!emailField.getText().isEmpty()) {
            email = emailField.getText();
        }
        if (!ageField.getText().isEmpty()) {
            age = Integer.parseInt(ageField.getText());
        }
        if (!nameField.getText().isEmpty()) {
            name = nameField.getText();
        }
        Stage stage;
        Scene scene;
        Parent root;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("signupSecondPage.fxml"));
        root = loader.load();

        signupSecondPageController secondPage = loader.getController();
        secondPage.roleSelector.setValue(secondPage.roleSelector.getItems().getFirst());
        secondPage.setVars(email, age, name);

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


    }


    @FXML
    public void switchToLogin(ActionEvent event) throws IOException {
        Stage stage;
        Scene scene;
        Parent root;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        root = loader.load();

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
