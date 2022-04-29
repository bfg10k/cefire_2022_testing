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

    public UserRepository(Gson gson) {
        this.gson = gson;
    }

    public void saveUsers(String homeDir, List<User> persistedUsers) throws IOException {
        Files.writeString(Paths.get(homeDir + "/library/users.json"), this.gson.toJson(persistedUsers));
    }

    public List<User> getUsers(String homeDir) throws IOException {
        String text = Files.readString(Paths.get(homeDir + "/library/users.json"));
        Type persistedUsersType = new TypeToken<ArrayList<User>>() {
        }.getType();
        return this.gson.fromJson(!text.equals("") ? text:"[]", persistedUsersType);
    }
}