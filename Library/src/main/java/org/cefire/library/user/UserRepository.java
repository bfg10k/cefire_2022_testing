package org.cefire.library.user;

import java.io.IOException;

public interface UserRepository {
    void saveUser(User user) throws IOException;
}
