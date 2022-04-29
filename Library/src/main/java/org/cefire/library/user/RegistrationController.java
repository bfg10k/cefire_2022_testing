package org.cefire.library.user;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.cefire.library.util.json.deserializer.LocalDateDeserializer;
import org.cefire.library.util.json.serializer.LocalDateSerializer;

import spark.Request;
import spark.Response;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegistrationController {

    public static String register(Request request, Response response) {
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateDeserializer())
                .registerTypeAdapter(LocalDate.class, new LocalDateSerializer()).setPrettyPrinting().create();

        String fullname = request.queryParamOrDefault("fullname", "");
        String dni = request.queryParamOrDefault("dni", "");
        String strDate = request.queryParamOrDefault("birthdate", "");
        String homeDir = System.getenv("USERPROFILE");


        try {
            LocalDate birthdate = LocalDate.parse(strDate, DateTimeFormatter.ISO_DATE);
            User user = new User(fullname, dni, birthdate);

            List<User> persistedUsers = getUsers(gson, homeDir);

            persistedUsers.add(user);
            saveUsers(gson, homeDir, persistedUsers);

            Map<String, User> successContent = new HashMap<>();
            successContent.put("registeredUser", user);
            return gson.toJson(successContent);
        } catch (NonValidDniException | NonValidNameException | NonValidAgeException exception) {
            response.status(422);
            Map<String, String> errorContent = new HashMap<>();
            errorContent.put("error", "101");
            errorContent.put("message", "Name, dni, or date are not valid.");
            return gson.toJson(errorContent);
        } catch (Exception exception) {
            response.status(500);
            Map<String, String> errorContent = new HashMap<>();
            errorContent.put("error", "201");
            errorContent.put("message", "Could not save User. Try later.");
            return gson.toJson(errorContent);
        }
    }

    protected static void saveUsers(Gson gson, String homeDir, List<User> persistedUsers) throws IOException {
        Files.writeString(Paths.get(homeDir + "/library/users.json"), gson.toJson(persistedUsers));
    }

    protected static List<User> getUsers(Gson gson, String homeDir) throws IOException {
        String text = Files.readString(Paths.get(homeDir + "/library/users.json"));
        Type persistedUsersType = new TypeToken<ArrayList<User>>() {
        }.getType();
        List<User> persistedUsers = gson.fromJson(!text.equals("") ? text : "[]" , persistedUsersType);
        return persistedUsers;
    }
}
