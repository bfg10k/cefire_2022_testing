package org.cefire.library;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.cefire.library.user.RegistrationController;
import org.cefire.library.util.json.deserializer.LocalDateDeserializer;

import java.time.LocalDate;

import static spark.Spark.*;

public class Main {
    public static void main(String[] args) {
        post("/register", RegistrationController::register);
    }
}
