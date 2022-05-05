package org.cefire.library.user;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.cefire.library.util.json.deserializer.LocalDateDeserializer;
import org.cefire.library.util.json.serializer.LocalDateSerializer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;

public class UserRegistrationTest {
    @Test
    public void canRegisterUserOlderThanThresshold() throws IOException {
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateDeserializer())
                .registerTypeAdapter(LocalDate.class, new LocalDateSerializer()).setPrettyPrinting().create();
         User user = new RegistrationUseCase(new TestUserRepository(gson))
                 .execute("José Manuel", "11111111H", "1983-06-04");

        Assertions.assertEquals(user.getFullname() , "José Manuel");
        Assertions.assertEquals(user.getDni() ,"11111111H");
        Assertions.assertEquals(user.getBirthdate().toString() ,"1983-06-04");
    }
}
