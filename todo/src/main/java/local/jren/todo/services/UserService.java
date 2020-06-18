package local.jren.todo.services;

import local.jren.todo.models.User;
import local.jren.todo.views.TodoCounts;

import java.util.List;

public interface UserService {

    List<User> findAllUsers();
    User findUserById(long id);

    void delete(long id);
    User save(User user);
    List<TodoCounts> getTodoCountsByUser();
}
