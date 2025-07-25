package sbu.cs.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sbu.cs.demo.openpage.OpenPage;
import sbu.cs.demo.scene.entrance.AccountManager;
import sbu.cs.demo.scene.entrance.VisibleData;

import java.io.IOException;

public class LoginController  {

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;

    @FXML
    private void handleLogin(ActionEvent event) throws IOException { // login page
        String username = usernameField.getText();
        String password = passwordField.getText();


        if (AccountManager.findAccount(username, password)) {
            System.out.println("Login Successful!");

        try {
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("mainpage.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//            Stage stage = new Stage();
            stage.close();
            stage.setScene(new Scene(root));
            stage.show();
        }catch (Exception e) {
            System.out.println("Error loading mainpage.fxml " + e.getMessage());
            e.printStackTrace();
        }

        } else {
            System.out.println("Invalid Credentials!");
        }
    }

    @FXML
    private void switchToSignup(ActionEvent event)throws IOException {
        switchToScene("Signup.fxml" , event);
    }

    private void switchToScene(String fxml , ActionEvent event) throws IOException {

        Stage stage;
        Scene scene;
        Parent root;

        root = FXMLLoader.load(getClass().getResource(fxml));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }



}
