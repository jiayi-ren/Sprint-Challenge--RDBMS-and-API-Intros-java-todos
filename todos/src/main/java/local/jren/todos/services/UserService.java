package local.jren.todos.services;

import local.jren.todos.models.User;

import java.util.List;

public interface UserService {
    List<User> findAllUsers();
    User findUserById(long id);

    void deleteUserById(long id);
    User save(User user);
    void deleteUserTodo(long userid, long todoid);
    void addUserTodo(long userid, long todoid);
}
