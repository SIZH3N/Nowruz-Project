package sbu.cs.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sbu.cs.demo.data.songsAndAlbum.Album;
import sbu.cs.demo.data.songsAndAlbum.AlbumFile;
import sbu.cs.demo.data.songsAndAlbum.Song;
import sbu.cs.demo.data.songsAndAlbum.SongFile;
import sbu.cs.demo.openpage.OpenPage;
import sbu.cs.demo.scene.entrance.VisibleData;

import java.io.IOException;
import java.nio.channels.NonWritableChannelException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class NewAlbumController {
    public TableView<Song> songTable;
    public TableColumn<Song, String> titleColumn;
    public TableColumn<Song, String> IDColumn;
    public DatePicker realseDatePicker;
    public Label albumTitle1;

    @FXML
    private static String albumTitle;

    public static List<Song> songs;


    public void addButton(ActionEvent event) throws IOException {
        OpenPage<SongCreateController> newSong = new OpenPage<SongCreateController>();

        FXMLLoader fxmlLoader = newSong.setLoader("songCreate.fxml");
        SongCreateController controller = newSong.setcontroller(fxmlLoader);
        controller.setSongs(songs, albumTitle);
        newSong.setStage(event);
        newSong.getStage().setResizable(true);
        newSong.showStage();
    }

    public void deleteButton(ActionEvent event) {
        int selectedIndex = songTable.getSelectionModel().getSelectedIndex();
        System.out.println("selected index: " + selectedIndex);

        if (selectedIndex == -1) {
            // No selection, show a warning or return
            System.out.println("No song selected to delete.");
            return;
        }
        songTable.getItems().remove(selectedIndex);
        songs.remove(selectedIndex);
        System.out.println("Deleted song at index: " + selectedIndex);
    }

    public static void addSong(Song song) {
        songs.add(song);
    }

    public void saveButton(ActionEvent event) throws IOException {
        // Extract only the titles
        List<String> titles = songs.stream()
                .map(Song::getTitle) // Extract the title
                .toList();

        int id;
        if (AlbumFile.getAlbum().isEmpty()) {
            id = 0;
        } else {
            id = AlbumFile.getAlbum().getLast().getID() + 1;
        }
        Album newAlbum = new Album(albumTitle, VisibleData.getInstance().getUsername(), id, titles);
        AlbumFile.addAlbum(newAlbum);
        SongFile.addSong(songs);
        songs.clear();

        openPage(event);
    }

    public void cancelButton(ActionEvent event) throws IOException {

        songs.clear();

        openPage(event);
    }

    private void openPage(ActionEvent event) throws IOException {
        try {

            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("/sbu/cs/demo/mainpage.fxml"));
            Parent root = loader.load();

            if (loader == null) {
                System.out.println("Loader is null");
            }

            MainpageController loginController = loader.getController();


            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(true);
            stage.show();


        } catch (Exception e) {
            System.out.println("Error opening main page: " + e.getMessage());
        }

    }

    public void initialize() {
        ObservableList<Song> songData = FXCollections.observableArrayList(NewAlbumController.songs);
        songTable.setItems(songData);
        titleColumn.setCellValueFactory(new PropertyValueFactory<Song, String>("title"));
        IDColumn.setCellValueFactory(new PropertyValueFactory<Song, String>("ID"));


        System.out.println("  size  " + NewAlbumController.songs.size());

        if (NewAlbumController.songs.isEmpty()) {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("New Album");
            dialog.setHeaderText("Create a new Album");
            dialog.setContentText("Enter Album title:");

            Optional<String> result = dialog.showAndWait();
            result.ifPresent(title -> {
                System.out.println("New Album title: " + title);
                albumTitle = title;
            });
        }
        albumTitle1.setText(albumTitle);

    }
}
