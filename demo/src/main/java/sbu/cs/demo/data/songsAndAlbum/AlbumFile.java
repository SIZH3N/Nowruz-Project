package sbu.cs.demo.data.songsAndAlbum;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AlbumFile {

    private static List<Album> albums;

    private static final String jsonFile = "src/main/java/sbu/cs/demo/data/songsAndAlbum/Albums.json";

    public static List<Album> readAlbums(){
         albums = new ArrayList<>();
        try (Reader reader = new FileReader(jsonFile)) {
            Gson gson = new Gson();
            albums = gson.fromJson(reader, new TypeToken<List<Album>>() {}.getType());
        } catch (IOException e) {
            System.err.println("Error reading JSON file: " + e.getMessage());
        }
        return albums;
    }

    public static List<Album> getAlbum(){
        albums = readAlbums();
        if (albums == null) {
            albums = new ArrayList<>();
        }
        return albums;
    }

    public static void addAlbum(Album newAlbum) {
        albums = readAlbums();
        if (albums == null) {
            albums = new ArrayList<>();
        }
        albums.add(newAlbum);
        writeAlbums();
    }

    public static void writeAlbums() {
        if (albums == null) {
            albums = new ArrayList<>();
        }
        try (Writer writer = new FileWriter(jsonFile)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(AlbumFile.albums, writer);
            System.out.println("Saved: " + AlbumFile.albums.size() + " Suggestions");
        } catch (IOException e) {
            System.err.println("Error writing JSON file: " + e.getMessage());
        }
    }
}
