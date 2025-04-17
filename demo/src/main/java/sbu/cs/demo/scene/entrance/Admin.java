package sbu.cs.demo.scene.entrance;

import sbu.cs.demo.MainpageController;

import java.util.List;

public class Admin extends Account {
    public Admin(String name, int age, String email, String username, String password, String role, List<String> followedArtists) {
        super(name, age, email, username, password, role, followedArtists);
    }

    @Override
    public void displayProfile() {
        System.out.println("Admin Dashboard Access.");
    }

    public void approveArtist(String artistName) {
        System.out.println("Artist " + artistName + " approved.");
    }

    public void showButtons(MainpageController controller) {
//        controller.showAdminButtons();
    }


}
