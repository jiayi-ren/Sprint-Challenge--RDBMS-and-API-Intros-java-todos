package local.jren.todo.services;

import local.jren.todo.models.Todos;
import local.jren.todo.models.User;
import local.jren.todo.repositories.UserRepository;
import local.jren.todo.views.TodoCounts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service(value = "userService")
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAllUsers() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().iterator().forEachRemaining(users::add);

        return users;
    }

    @Override
    public User findUserById(long id) {
        return userRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("User "+id+" Not Found"));
    }

    @Transactional
    @Override
    public void delete(long id) {
        if (userRepository.findById(id).isPresent()) {
            userRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("User "+id+" Not Found");
        }
    }

    @Transactional
    @Override
    public User save(User user) {

        User newUser = new User();

        newUser.setUsername(user.getUsername());
        newUser.setPassword(user.getPassword());
        newUser.setPrimaryemail(user.getPrimaryemail());

        newUser.getTodos().clear();
        for (Todos todo : user.getTodos()) {
            Todos newTodo = new Todos(newUser, todo.getDescription());
            newUser.getTodos().add(newTodo);
        }

        return userRepository.save(newUser);
    }

    @Override
    public List<TodoCounts> getTodoCountsByUser() {
        return userRepository.getTodoCountsByUser();
    }
}
