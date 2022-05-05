package org.cefire.library.user;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    private final Gson gson;
    private final String homeDir;

    public UserRepository(Gson gson) {
        this.gson = gson;
        this.homeDir = System.getenv("USERPROFILE");

    }

    protected void saveUser(List<User> persistedUsers) throws IOException {

        Files.writeString(Paths.get(this.homeDir + "/library/users.json"), this.gson.toJson(persistedUsers));
    }

    protected List<User> getUsers() throws IOException {
        Type persistedUsersType = new TypeToken<ArrayList<User>>() {
        }.getType();
        return this.gson.fromJson(Files.readString(Paths.get(this.homeDir + "/library/users.json")), persistedUsersType);
    }
}
