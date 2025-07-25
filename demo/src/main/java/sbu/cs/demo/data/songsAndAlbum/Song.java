package sbu.cs.demo.data.songsAndAlbum;

import java.util.ArrayList;
import java.util.List;

import sbu.cs.demo.data.Comment;

public class Song {
    private int ID;
    private String title;
    private String lyrics;
    private String artist;
    private String album;
    private int views;
    private List<Comment> comments;
    private String releaseDate;

    public Song(String title, String lyric, String artist, String album, String releaseDate, int id) {
        this.title = title;
        this.lyrics = lyric;
        this.artist = artist;
        this.album = album;
        this.views = 0;
        this.comments = new ArrayList<>();
        this.releaseDate = releaseDate;
        this.ID = id;
    }

    public Song() {
        this.title = null;
        this.lyrics = null;
        this.artist = null;
        this.views = 0;
        this.comments = null;
        this.releaseDate = null;
        this.ID = 0;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void playSong() {
        views++;
        System.out.println("Playing: " + title + "\nLyrics:\n" + lyrics);
    }

    public String getTitle() {
        return title;
    }

    public String getAlbum() {
        return album;
    }

    public String getLyrics() {
        return lyrics;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public int getViews() {
        return views;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getArtist() {
        return artist;
    }

}
