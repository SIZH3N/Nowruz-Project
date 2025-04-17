package sbu.cs.demo.scene.entrance;

import sbu.cs.demo.MainpageController;

import java.util.ArrayList;
import java.util.List;

public class Artist extends Account {
    private List<String> songs;

    public Artist(String name, int age, String email, String username, String password, String role, List<String> followedArtists) {
        super(name, age, email, username, password, role, followedArtists);
        this.songs = new ArrayList<String>();
    }

    public void addSong(String song) {
        songs.add(song);
    }

    @Override
    public void displayProfile() {
        System.out.println("Artist Profile: " + username + " has songs " + songs);
    }


    public void showButtons(MainpageController controller) {

    }
}
