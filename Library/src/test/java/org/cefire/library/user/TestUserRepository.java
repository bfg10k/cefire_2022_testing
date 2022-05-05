package org.cefire.library.user;

import com.google.gson.Gson;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TestUserRepository extends UserRepository{

    public TestUserRepository(Gson gson) {
        super(gson);
    }

    @Override
    protected void saveUser(List<User> persistedUsers) throws IOException {
    }

    @Override
    protected List<User> getUsers() throws IOException {
        return new ArrayList<>();
    }
}
