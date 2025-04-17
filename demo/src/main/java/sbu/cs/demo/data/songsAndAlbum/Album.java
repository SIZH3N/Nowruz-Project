package sbu.cs.demo.data.songsAndAlbum;

import sbu.cs.demo.scene.entrance.Artist;

import java.util.ArrayList;
import java.util.List;

public class Album {
    private int ID;
    private String title;
    private String artist;
    private List<String> tracklist;

    public Album(String title, String artist, int ID , List<String> tracklist) {
        this.title = title;
        this.artist = artist;
        this.ID = ID;
        if (tracklist != null) {
            this.tracklist = tracklist;
        } else {
            this.tracklist = new ArrayList<>();
        }
    }

    public void addSong(Song song) {
        tracklist.add(song.getTitle());
    }

    public void setID(int ID) {
        this.ID = AlbumFile.getAlbum().getLast().ID + 1;
    }

    public int getID() { return ID; }

    public void displayAlbum() {
        System.out.println("Album: " + title + " by " + artist);
        tracklist.forEach(song -> System.out.println(" - " + song));
    }
}
