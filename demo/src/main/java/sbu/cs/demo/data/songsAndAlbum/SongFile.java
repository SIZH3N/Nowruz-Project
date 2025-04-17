package sbu.cs.demo.data.songsAndAlbum;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SongFile {

    private static final String jsonFile = "src/main/java/sbu/cs/demo/data/songsAndAlbum/Songs.json";

    public static List<Song> readSongs() {
        List<Song> songs = new ArrayList<>();
        try (Reader reader = new FileReader(jsonFile)) {
            Gson gson = new Gson();
            songs = gson.fromJson(reader, new TypeToken<List<Song>>() {
            }.getType());
        } catch (IOException e) {
            System.err.println("Error reading JSON file: " + e.getMessage());
        }
        return songs;
    }

    public static void addSong(Song newSong) {
        List<Song> songs = SongFile.readSongs();
        if (songs == null) {
            songs = new ArrayList<>();
        }
//        for (Song song : songs) {
//            System.out.println(song.getTitle() + "                   ()))");
//        }
//        if (songs != null) {
        if (newSong != null)
            songs.add(newSong);
        writeSongs(songs);
//    }
    }

    public static void addSong(List<Song> songs) {
        List<Song> allSongs = SongFile.readSongs();
        if (allSongs == null) {
            allSongs = new ArrayList<>();
        }
        for (Song song : songs) {
            if (song != null)
                allSongs.add(song);
        }
        writeSongs(allSongs);
    }

    public static void writeSongs(List<Song> songs) {
        try (Writer writer = new FileWriter(jsonFile)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(songs, writer);
            //System.out.println("Saved: " + songs.size() + " Suggestions");
        } catch (IOException e) {
            System.err.println("Error writing JSON file: " + e.getMessage());
        }
    }

    public static Song getSongByID(int id) {
        List<Song> songs = readSongs();
        for (Song song : songs) {
            if (song.getID() == id) {
                return song;
            }
        }
        return null;
    }

    public static void changeSong(Song song) {
        List<Song> songs = readSongs();
        for (int i = 0; i < songs.size(); i++) {
            if (songs.get(i).getID() == song.getID()) {
                songs.set(i, song);
                break;
            }
        }
        writeSongs(songs);
    }

}
