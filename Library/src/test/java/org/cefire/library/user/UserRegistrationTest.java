package org.cefire.library.user;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import spark.Request;

public class UserRegistrationTest {
    @Test
    public void canRegisterAdultUsers(){
       String jsonResponse = RegistrationControllerTest.register(new TestRequest(), new TestResponse());

        Assertions.assertEquals("{\n" +
                "  \"registeredUser\": {\n" +
                "    \"fullName\": \"José\",\n" +
                "    \"dni\": \"11111111H\",\n" +
                "    \"birthdate\": \"1988-04-08\"\n" +
                "  }\n" +
                "}", jsonResponse );
    }
}
