package org.cefire.library.user;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.cefire.library.util.json.deserializer.LocalDateDeserializer;
import org.cefire.library.util.json.serializer.LocalDateSerializer;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class RegistrationUseCase {
    private final UserRepository userRepository;

    public RegistrationUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User execute(String fullname, String dni, String strDate) throws IOException {
        LocalDate birthdate = LocalDate.parse(strDate, DateTimeFormatter.ISO_DATE);
        User user = new User(fullname, dni, birthdate);

        List<User> persistedUsers = this.userRepository.getUsers();

        persistedUsers.add(user);
        this.userRepository.saveUser(persistedUsers);

        return user;
    }
}
