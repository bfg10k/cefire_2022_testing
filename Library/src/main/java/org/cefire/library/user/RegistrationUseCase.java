package org.cefire.library.user;

import com.google.gson.Gson;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class RegistrationUseCase {
    private final UserRepository userRepository;

    public RegistrationUseCase( UserRepository userRepository) {

        this.userRepository=userRepository;
    }

    public User execute(String fullname, String dni, String strDate) throws IOException {
        String homeDir = System.getenv("USERPROFILE");

        LocalDate birthdate = LocalDate.parse(strDate, DateTimeFormatter.ISO_DATE);
        User user = new User(fullname, dni, birthdate);

        List<User> persistedUsers = this.userRepository.getUsers( homeDir);

        persistedUsers.add(user);
        this.userRepository.saveUsers(homeDir, persistedUsers);

        return user;
    }
}
