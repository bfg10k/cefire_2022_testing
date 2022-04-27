package org.cefire.library.user;

import org.cefire.library.User;
import org.cefire.library.UserShouldBeAnAdultException;
import org.cefire.library.UsernameDoesNotMeetLenghtException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserCreationTest {

    @Test
    public void shouldBeAnAdult(){
        Assertions.assertDoesNotThrow(()-> new User(18,"Adrián"));
        Assertions.assertDoesNotThrow(()-> new User(47,"Adrián"));
        Assertions.assertDoesNotThrow(()-> new User(64,"Adrián"));
    }

    @Test
    public void shouldBeYoungerThanThresshold(){
        Assertions.assertThrows(UserShouldBeAnAdultException.class, ()-> new User(65,"Adrián"));
        Assertions.assertThrows(UserShouldBeAnAdultException.class, ()-> new User(128,"Adrián"));
    }

    @Test
    public void cantBeMinor(){
        Assertions.assertThrows(UserShouldBeAnAdultException.class, ()-> new User(17,"Adrián"));
        Assertions.assertThrows(UserShouldBeAnAdultException.class, ()-> new User(0,"Adrián"));
    }

    @Test
    public void shouldBeLongerThanThresshold(){
        Assertions.assertThrows(UsernameDoesNotMeetLenghtException.class, ()->new User(27, "Aa"));
        Assertions.assertThrows(UsernameDoesNotMeetLenghtException.class, ()->new User(27, ""));
    }

    @Test
    public void shouldBeShorterThanThresshold(){
        Assertions.assertThrows(UsernameDoesNotMeetLenghtException.class, ()->new User(27, "Aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
        Assertions.assertThrows(UsernameDoesNotMeetLenghtException.class, ()->new User(27, "AaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaAaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaAaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
    }

    @Test
    public void shouldCointainValidCharacters(){
        Assertions.assertThrows(UsernameDoesNotMeetLenghtException.class, () -> new User(27, "%%%%%"));
        Assertions.assertThrows(UsernameDoesNotMeetLenghtException.class, () -> new User(27, "%%$$$"));
    }

    @Test
    public void canCreateUserWithValidName(){
        Assertions.assertDoesNotThrow(()-> new User(27, "Manuel"));
        Assertions.assertDoesNotThrow(()-> new User(27, "José Manuel"));
        Assertions.assertDoesNotThrow(()-> new User(27, "José Manñçel"));
        Assertions.assertDoesNotThrow(()-> new User(27, "José Manïèl"));
    }
}
