package org.cefire.library.user;

import java.time.LocalDate;
import java.time.Period;

public class User {

    private final String fullname;
    private final String dni;
    private final LocalDate birthdate;

    public User(String fullname, String dni, LocalDate birthdate) {
        if (!fullname.matches("[A-Za-zÀ-ÖØ-öø-ÿ ]{3,40}")) {
            throw new NonValidNameException();
        }

        if (!dniIsValid(dni)) {
            throw new NonValidDniException();
        }

        if (!ageIsValid(birthdate)) {
            throw new NonValidAgeException();
        }

        this.fullname = fullname;
        this.dni = dni;
        this.birthdate = birthdate;
    }

    public String getFullname() {
        return fullname;
    }

    public String getDni() {
        return dni;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    private boolean dniIsValid(String dni) {
        try {
            int dniNumericPart = Integer.parseInt(dni.substring(0, 8));
            String dniLetter = dni.substring(8).toUpperCase();

            return this.validateLetter(dniNumericPart, dniLetter);
        } catch (NumberFormatException exception) {
            throw new NonValidDniException(exception);
        }
    }

    private boolean ageIsValid(LocalDate birthDate) {
        int age = ageFromDate(birthDate);
        return age >= 6 && age <= 125;
    }

    private int ageFromDate(LocalDate birthDate) {
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

    private boolean validateLetter(int number, String letter) {
        String[] orderedValidLetters = {"T", "R", "W", "A", "G", "M", "Y", "F", "P", "D", "X", "B", "N", "J", "Z", "S", "Q", "V", "H", "L", "C", "K", "E"};

        return orderedValidLetters[number % 23].equals(letter);
    }
}