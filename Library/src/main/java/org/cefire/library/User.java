package org.cefire.library;

public class User {
    private final int age;
    private final String name;

    public User(int age, String name) {
        if (age < 18 || age >= 65) {
            throw new UserShouldBeAnAdultException();
        }
        if (!name.matches("[A-Za-zÀ-ÖØ-öø-ÿ ]{3,40}")) {
            throw new UsernameDoesNotMeetLenghtException();
        }

        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }
}
