package org.cefire.library.user;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import static org.junit.jupiter.api.Assertions.*;


public class UserCreationTest {

    @Test
    public void canBeCreatedWithValidAge(){
        assertDoesNotThrow(()-> new User("UnNombreValido", "35430884M", dateYearsAgo(6)));
        assertDoesNotThrow(()-> new User("UnNombreValido", "35430884M", dateYearsAgo(55)));
        assertDoesNotThrow(()-> new User("UnNombreValido", "35430884M", dateYearsAgo(125)));
    }

    @Test
    public void shouldBeYoungerThanThresshold(){
        assertThrows(NonValidAgeException.class, ()-> new User("UnNombreValido", "35430884M", dateYearsAgo(126)));
        assertThrows(NonValidAgeException.class, ()-> new User("UnNombreValido", "35430884M", dateYearsAgo(180)));
    }

    @Test
    public void shouldBeOlderThanThresshold(){
        assertThrows(NonValidAgeException.class, ()-> new User("UnNombreValido", "35430884M", dateYearsAgo(5)));
        assertThrows(NonValidAgeException.class, ()-> new User("UnNombreValido", "35430884M", dateYearsAgo(2)));
        assertThrows(NonValidAgeException.class, ()-> new User("UnNombreValido", "35430884M", dateYearsAgo(0)));
    }

    @Test
    public void fullnameShouldBeLongerThanThresshold(){
        assertThrows(NonValidNameException.class, ()-> new User("Aa", "35430884M", dateYearsAgo(16)));
        assertThrows(NonValidNameException.class, ()-> new User("", "35430884M", dateYearsAgo(16)));

    }

    @Test
    public void fullnameShouldBeShorterThanThresshold(){
        assertThrows(NonValidNameException.class, ()-> new User("thisStringIsLongerThanThressholdByJustOne", "35430884M", dateYearsAgo(16)));
        assertThrows(NonValidNameException.class, ()-> new User("thisStringIsQuiteLongerThanThresshold cL2pZ4azQHYWFtaqrAQlMMHE3R92upwn2JicamXHtvbxU9VY0c02", "35430884M", dateYearsAgo(16)));
    }

    @Test
    public void fullnameShouldCointainValidCharacters(){
        assertThrows(NonValidNameException.class, ()-> new User("thisStringContainsInvalidCharactersLike%Or$", "35430884M", dateYearsAgo(16)));
        assertThrows(NonValidNameException.class, ()-> new User("thisStringContainsInvalidCharactersLike_Or!And[][}", "35430884M", dateYearsAgo(16)));
    }

    @Test
    public void canCreateUserWithValidName(){
        assertDoesNotThrow(()-> new User("Bjørg", "35430884M", dateYearsAgo(16)));
        assertDoesNotThrow(()-> new User("José Manuel", "35430884M", dateYearsAgo(16)));
        assertDoesNotThrow(()-> new User("Björk", "35430884M", dateYearsAgo(16)));
        assertDoesNotThrow(()-> new User("François Roland Truffaut", "35430884M", dateYearsAgo(16)));
    }

    @Test
    public void cantBeCreatedWithInvalidDni(){
        assertThrows(NonValidDniException.class, ()-> new User("Bjørg", "35430884Z", dateYearsAgo(16)));
        assertThrows(NonValidDniException.class, ()-> new User("Bjørg", "UnDniInvalido", dateYearsAgo(16)));
        assertThrows(NonValidDniException.class, ()-> new User("Bjørg", "6666666666", dateYearsAgo(16)));
    }

    @Test
    public void canBeCreatedWithValidDni(){
        assertDoesNotThrow(()-> new User("Bjørg", "29131374B", dateYearsAgo(16)));
        assertDoesNotThrow(()-> new User("Bjørg", "55240842V", dateYearsAgo(16)));
        assertDoesNotThrow(()-> new User("Bjørg", "86574737T", dateYearsAgo(16)));
    }

    private LocalDate dateYearsAgo(int years) {
        return LocalDate.now().plus(-years, ChronoUnit.YEARS);
    }
}
