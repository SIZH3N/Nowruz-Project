package sbu.cs.demo.scene.entrance;


import sbu.cs.demo.MainpageController;

import java.util.List;
import java.util.ArrayList;

public class User extends Account {

    public User(String name, int age, String email, String username, String password, String role,List<String> followedArtists) {
        super(name, age, email, username, password, role, followedArtists);
//        super.followedArtists = new ArrayList<>();
    }

    public void followArtist(String artist) {
        super.followedArtists.add(artist);
    }

    @Override
    public void displayProfile() {
        System.out.println("User Profile: " + super.username + " follows " + followedArtists);
    }

    public void showButtons(MainpageController controller) {

    }
}
