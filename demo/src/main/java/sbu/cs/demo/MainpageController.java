package sbu.cs.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import sbu.cs.demo.data.songsAndAlbum.Song;
import sbu.cs.demo.data.songsAndAlbum.SongFile;
import sbu.cs.demo.openpage.OpenPage;
import sbu.cs.demo.scene.entrance.AccountManager;
import sbu.cs.demo.scene.entrance.VisibleData;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MainpageController implements Initializable {

    public TextField searchField;
    public Button searchButton;
    public Button loginButton;
    public Button signupButton;
    public TableView<Song> trendingSongsTableView;
    public TableColumn<Song, String> titleColumn;
    public TableColumn<Song, String> artistColumn;
    public TableColumn<Song, String> albumColumn;
    public TableColumn<Song, Integer> viewsColumn;
    public TableColumn<Song, String> releasedateColumn;
    public TableColumn<Song, Integer> IDColumn;
    public ImageView avatar;
    public Button newSongButton;
    public Button newArtistButton;
    public Button requestButton;

    public void signupButtonAction(ActionEvent event) throws IOException {
        OpenPage<Signup> signup = new OpenPage<Signup>();

        signup.setLoader("signup.fxml");
        signup.setStage(event);
        signup.getStage().setResizable(false);
        signup.showStage();
    }

    public void loginButtonAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(true);
        stage.show();
    }

    public void newSongButtonAction(ActionEvent event) throws IOException {
        if (VisibleData.getInstance().getRole() == null || !VisibleData.getInstance().getRole().equals("Artist") && !VisibleData.getInstance().getRole().equals("User")) {
            showAlertAccessDenied();
            return;
        }

        Stage stage;
        Scene scene;
        Parent root;

        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("songCreate.fxml"));
        root = loader.load();

        SongCreateController loginController = loader.getController();

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(true);
        stage.show();

    }

    public void newAlbumButtonAction(ActionEvent event) throws IOException {
        if (VisibleData.getInstance().getRole() == null || !VisibleData.getInstance().getRole().equals("Artist") && !VisibleData.getInstance().getRole().equals("User")) {
            showAlertAccessDenied();
            return;
        }
        NewAlbumController.songs = new ArrayList<>();

        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("newAlbum.fxml"));
        Parent root = loader.load();

        NewAlbumController loginController = loader.getController();

        Stage  stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene   scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(true);
        stage.show();
    }

    public void searchButtonAction(ActionEvent event) throws IOException {
//        switchPage("search.fxml",event);
        searchButtonAction();
    }

    public void homeButtonAction(ActionEvent event) throws IOException {

    }

    public void topSongsButtonAction(ActionEvent event) throws IOException {

    }

    public void artistsButtonAction(ActionEvent event) throws IOException {

    }

    public void albumsButtonAction(ActionEvent event) throws IOException {

    }

    @FXML
    public void resetButtonAction(ActionEvent event) throws IOException {
        searchField.clear();
        List<Song> songs = SongFile.readSongs();
        ObservableList<Song> songData = FXCollections.observableArrayList(songs);
        trendingSongsTableView.setItems(songData);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<Song> songs = SongFile.readSongs();
        ObservableList<Song> songData = FXCollections.observableArrayList(songs);
        trendingSongsTableView.setItems(songData);

        titleColumn.setCellValueFactory(new PropertyValueFactory<Song, String>("title"));
        artistColumn.setCellValueFactory(new PropertyValueFactory<Song, String>("artist"));
        albumColumn.setCellValueFactory(new PropertyValueFactory<Song, String>("album"));
        viewsColumn.setCellValueFactory(new PropertyValueFactory<Song, Integer>("views"));
        releasedateColumn.setCellValueFactory(new PropertyValueFactory<Song, String>("releaseDate"));
        IDColumn.setCellValueFactory(new PropertyValueFactory<Song, Integer>("ID"));

        trendingSongsTableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) { // Double-click detected
                Song selectedSong = trendingSongsTableView.getSelectionModel().getSelectedItem();
                if (selectedSong != null) {
                    try {
                        openSongDetailsPage(selectedSong);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

    }

    public void openProfilePage() throws IOException {
        if(VisibleData.getInstance().getUsername() == null){

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("You are not logged in");
            alert.setContentText("Please log in to access your profile.");
            alert.showAndWait();
            return;
        }
        System.out.println(VisibleData.getInstance().getUsername());


        FXMLLoader loader = new FXMLLoader(getClass().getResource("profile.fxml"));
        Parent root = loader.load();
        ProfileController profile = loader.getController();

        AccountManager.getAccountData(profile, VisibleData.getInstance().getUsername());
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }

    private void openSongDetailsPage(Song song) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("songDetails.fxml"));
        Parent root = loader.load();

//        System.out.println(song.getID());

        SongDetailsController controller = loader.getController();
        if (controller != null) {
            controller.setSongDetails(song);
        } else {
            throw new IOException("Failed to load SongDetailsController.");
        }
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);

        controller.setStageCloseHandler(stage);

        stage.show();
    }

    private void searchButtonAction() {
        String searchText = searchField.getText().toLowerCase();
        List<Song> songs = SongFile.readSongs();
        ObservableList<Song> songData = FXCollections.observableArrayList(songs);

        if (searchText.isEmpty()) {
            trendingSongsTableView.setItems(songData);
        } else {
            ObservableList<Song> filteredData = FXCollections.observableArrayList();
            for (Song song : songData) {
                boolean title =song.getTitle()!= null && song.getTitle().toLowerCase().contains(searchText)  ;
                boolean artist = song.getArtist()!= null && song.getArtist().toLowerCase().contains(searchText) ;
                boolean lyrics =song.getLyrics()!= null &&  song.getLyrics().toLowerCase().contains(searchText) ;
                boolean album = song.getAlbum() != null && song.getAlbum().toLowerCase().contains(searchText) ;
                if (title || artist || lyrics || album) {
                    filteredData.add(song);
                }
            }
            trendingSongsTableView.setItems(filteredData);
        }
    }

    private void showAlertAccessDenied() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Access Denied");
        alert.setHeaderText("You cannot access this feature");
        alert.setContentText("Only Artists and Admins can access this feature");
        alert.showAndWait();
    }

    public void logout(){
        VisibleData.getInstance().setUsername(null);
        VisibleData.getInstance().setRole(null);
        Dialog<String> dialog= new Dialog<String>();
        dialog.setTitle("Logout");
        dialog.setHeaderText("You have been logged out");
        dialog.setContentText("You can log in again");
        ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().add(okButton);
        dialog.showAndWait();
    }

}
