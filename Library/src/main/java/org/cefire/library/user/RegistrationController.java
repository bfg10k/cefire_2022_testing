package org.cefire.library.user;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.cefire.library.util.json.deserializer.LocalDateDeserializer;
import org.cefire.library.util.json.serializer.LocalDateSerializer;

import spark.Request;
import spark.Response;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

        try {
            User user = new RegistrationUseCase(new UserRepository(gson)).execute(fullname,dni, strDate);

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
}