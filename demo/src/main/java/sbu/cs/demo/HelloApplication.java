package sbu.cs.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sbu.cs.demo.data.songsAndAlbum.Song;
import sbu.cs.demo.data.songsAndAlbum.SongFile;
import sbu.cs.demo.openpage.OpenPage;

import java.io.IOException;
import java.util.Date;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("mainpage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Genius");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}