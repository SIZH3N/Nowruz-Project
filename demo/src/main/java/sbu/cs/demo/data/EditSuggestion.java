package sbu.cs.demo.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.util.*;

public class EditSuggestion {

   private static final String jsonFile = "src/main/java/sbu/cs/demo/data/Suggestion.json";

    public static List<Suggestion> readSuggestions() {
        List<Suggestion> suggestions = new ArrayList<>();
        try (Reader reader = new FileReader(jsonFile)) {
            Gson gson = new Gson();
            suggestions = gson.fromJson(reader, new TypeToken<List<Suggestion>>() {
            }.getType());
        } catch (IOException e) {
            System.err.println("Error reading JSON file: " + e.getMessage());
        }
        return suggestions;
    }

    public static void deleteSuggestion(int ID) {
        List<Suggestion> suggestions = readSuggestions();
        for (Suggestion suggestion : suggestions) {
            if (suggestion.getID() == ID) {
                suggestions.remove(suggestion);
            }
        }
    }

    public static void addSuggestion(Suggestion newSuggestion) {
        List<Suggestion> suggestions = readSuggestions();
        suggestions.add(newSuggestion);
        writeSuggestions(suggestions);
    }

    public static void writeSuggestions(List<Suggestion> suggestions ){
        try (Writer writer = new FileWriter(jsonFile)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(suggestions, writer);
            System.out.println("Saved: " + suggestions.size() + " Suggestions");
        } catch (IOException e) {
            System.err.println("Error writing JSON file: " + e.getMessage());
        }

    }

}

