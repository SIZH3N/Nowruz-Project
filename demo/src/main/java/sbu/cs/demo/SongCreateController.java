package sbu.cs.demo;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sbu.cs.demo.data.songsAndAlbum.Song;
import sbu.cs.demo.data.songsAndAlbum.SongFile;
import sbu.cs.demo.openpage.OpenPage;
import sbu.cs.demo.scene.entrance.VisibleData;

import java.io.IOException;
import java.util.List;

public class SongCreateController {
    public TextArea songTextArea;
    public TextField titleTextField;
    public TextField albumTextField;
    public TextField genreTextField;
    public TextField tagTextField;
    public DatePicker realseDatePicker;
    public ChoiceBox<String> artistPick;

    private boolean mainpage = true;
    private List<Song> songs;

    public void createSong(ActionEvent event) throws IOException {
        String songText = songTextArea.getText();
        String titleText = titleTextField.getText();
        String albumText = albumTextField.getText();
        String genreText = genreTextField.getText();
        String tagText = tagTextField.getText();
        String releaseDateText = realseDatePicker.getValue().toString();

        int id = SongFile.readSongs().getLast().getID() + 1;
        Song song = new Song(titleText, songText, VisibleData.getInstance().getUsername(), albumText, releaseDateText, id);


        String path;
        if (mainpage) {
            SongFile.addSong(song);
            path = "mainpage.fxml";
            OpenPage<MainpageController> mainpage = new OpenPage<>();
            mainpage.setLoader(path);
            mainpage.setStage(event, true);
        } else {
            songs.add(song);
            path = "newAlbum.fxml";
            OpenPage<NewAlbumController> mainpage = new OpenPage<>();
            mainpage.setLoader(path);
            mainpage.setStage(event, true);
        }

    }

    public void setSongs (List<Song> songs , String albumTitle) {
        this.songs = songs;
        this.albumTextField.setText(albumTitle);
        this.mainpage = false;
    }

    public void initialize() {
        titleTextField.setText("");
        albumTextField.setText("");
        genreTextField.setText("");
        tagTextField.setText("");
        songTextArea.setText("");
        artistPick.getItems().addAll(VisibleData.getInstance().getUsername());
        artistPick.setValue(VisibleData.getInstance().getUsername());
    }

}
