package sbu.cs.demo;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import sbu.cs.demo.data.Comment;
import sbu.cs.demo.data.songsAndAlbum.Song;
import sbu.cs.demo.data.songsAndAlbum.SongFile;
import sbu.cs.demo.scene.entrance.VisibleData;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class SongDetailsController implements Initializable {

    public TextArea commentInput;
    public ListView<Comment> commentList;
    @FXML
    private Label releasedLabel;
    @FXML
    private Label titleLabel;
    @FXML
    private Label artistLabel;
    @FXML
    private Label albumLabel;
    @FXML
    private Label viewLabel;
    @FXML
    private TextArea songTextArea;

    private Song song;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void postComment() {
        String comment = commentInput.getText().trim();
        if (!comment.isEmpty()) {
            Date date = new Date();
            Comment comment1 = new Comment(VisibleData.getInstance().getUsername(), comment, date);
            this.song.addComment(comment1);
            commentList.getItems().add(comment1);
            commentInput.clear();
        }
    }

    public void setSongDetails(Song song) {
        this.song = song;
        titleLabel.setText(song.getTitle());
        artistLabel.setText(song.getArtist());
        if (song.getAlbum() != null) {
            albumLabel.setText(albumLabel.getText() + " " + song.getAlbum());
        } else {
            albumLabel.setDisable(true);
        }
        releasedLabel.setText(releasedLabel.getText() + " " + song.getReleaseDate());
        viewLabel.setText(viewLabel.getText() + " " + song.getViews());
        songTextArea.setText(song.getLyrics());

        commentList.getItems().clear();
//        commentList.setEditable(false);
        commentList.getItems().addAll(song.getComments());

    }

    @FXML
    private void onClose() {
        SongFile.changeSong(song);
    }

    public void setStageCloseHandler(Stage stage) {
        stage.setOnCloseRequest(event -> {
            onClose(); // Call the custom close method
        });
    }


}