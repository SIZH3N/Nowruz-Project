package sbu.cs.demo.openpage;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sbu.cs.demo.HelloApplication;
import sbu.cs.demo.Signup;

import java.io.IOException;

public class OpenPage<T> {

    private Stage stage;
    private Scene scene;
    private Parent root;

    public FXMLLoader setLoader(String path) throws IOException {   //1
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource(path));
        root = loader.load();
        return loader;
    }

    public T setcontroller(FXMLLoader loader) throws IOException {  // 2(optional)
        T page = loader.getController();
        return page;
    }

    public void setStage(ActionEvent event) { // 3
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
    }

    public void setStage(ActionEvent event, boolean show) {
        setStage(event);
        showStage();
    }

    public Stage getStage() { // 4(optional)
        return stage;
    }

    public void showStage() { // 5
        stage.show();
    }

    public void setdefaultPage(String path, ActionEvent event) throws IOException {
        FXMLLoader loader = setLoader(path);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow(); // Ensure stage is initialized
        setStage(event, true);
    }

    public void openMainPage() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("mainpage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Genius");
        stage.setScene(scene);
        stage.show();
    }
}
