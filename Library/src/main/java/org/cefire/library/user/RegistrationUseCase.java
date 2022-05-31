package org.cefire.library.user;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RegistrationUseCase {
    private final UserRepository userRepository;

    public RegistrationUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User execute(String fullname, String dni, String strDate) throws IOException {
        LocalDate birthdate = LocalDate.parse(strDate, DateTimeFormatter.ISO_DATE);
        User user = new User(fullname, dni, birthdate);

        this.userRepository.saveUser(user);

        return user;
    }
}
