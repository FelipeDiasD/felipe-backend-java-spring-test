package Service;

import model.User;

import java.util.List;

public interface UserService {

    public List<User> getAllUsers();
    public void createUser(User user);
    public void updateUser(Long id, User user);
    public void deleteUser(Long id);
}
