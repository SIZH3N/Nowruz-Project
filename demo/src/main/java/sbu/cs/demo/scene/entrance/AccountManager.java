package sbu.cs.demo.scene.entrance;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import sbu.cs.demo.ProfileController;

import java.io.*;
import java.util.*;

public class AccountManager {
    private static final String jsonFile = "src/main/java/sbu/cs/demo/scene/entrance/Users.json";

    // Read Accounts from JSON
    public static List<Account> readAccounts(String filePath) {
        List<Account> users = new ArrayList<>();
        try (Reader reader = new FileReader(filePath)) {
            Gson gson = new Gson();
            users = gson.fromJson(reader, new TypeToken<List<Account>>() {}.getType());
        } catch (IOException e) {
            System.err.println("Error reading JSON file: " + e.getMessage());
        }
        return users;
    }

    // Add a new Account and write back to JSON
    public static void addAccount(Account newAccount) {
        List<Account> users = readAccounts(jsonFile);
        if (users == null) {
            users = new ArrayList<>();
        }
        users.add(newAccount);

        for (Account user : users) {
            System.out.println(user.getAccountUsername());
        }

        try (Writer writer = new FileWriter(jsonFile)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(users, writer);
        } catch (IOException e) {
            System.err.println("Error writing JSON file: " + e.getMessage());
        }
    }

    public static void addAccount(Account newAccount, boolean artist) {
        String artistFile = "src/main/java/sbu/cs/demo/scene/entrance/ArtistWaitingList.json";
        List<Account> artistList = readAccounts(artistFile);
        if (artistList == null) {
            artistList = new ArrayList<>();
        }
        artistList.add(newAccount);
        try (Writer writer = new FileWriter(artistFile)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(artistList, writer);
        } catch (IOException e) {
            System.err.println("Error writing JSON file: " + e.getMessage());
        }
    }

    public static boolean UsernameTaken(String username) {
        List<Account> users = readAccounts(jsonFile);
        for (Account account : users) {
            if(account.getAccountUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public static boolean findAccount(String Username, String Password) {
        List<Account> users = readAccounts(jsonFile);
        for (Account account : users) {
            if(account.getAccountUsername().equals(Username) && account.getAccountPasword().equals(Password)) {
                VisibleData.getInstance().setRole(account.getAccountRole());
                VisibleData.getInstance().setUsername(account.getAccountUsername());
                return true;
            }
        }
        return false;
    }

    public static boolean getAccountData(ProfileController profileController, String Username) {
        List<Account> users = readAccounts(jsonFile);
        for (Account account : users) {
            if(account.getAccountUsername().equals(Username)) {
                profileController.setData(account.getAccountUsername(), account.password, account.getAccountname(), account.email, String.valueOf(account.age), account.getAccountRole());
                return true;
            }
        }
        return false;
    }

}
