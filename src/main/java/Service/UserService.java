package Service;

import Entity.User;
import Repository.UserRepository;

import java.sql.SQLException;

public class UserService {
    private UserRepository userRepository = new UserRepository();

    public UserService() throws SQLException, ClassNotFoundException {
    }

    public int importUser(User user) throws SQLException {
        return userRepository.importUser(user);
    }
}
