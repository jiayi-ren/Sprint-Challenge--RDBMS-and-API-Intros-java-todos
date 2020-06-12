package local.jren.todos.services;

import local.jren.todos.models.Todo;
import local.jren.todos.models.User;
import local.jren.todos.models.UserTodos;
import local.jren.todos.repositories.UserRepository;
import local.jren.todos.views.TodoCounts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "userService")
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TodoService todoService;

    @Autowired
    private UserAuditing userAuditing;

    @Override
    public List<User> findAllUsers() {

        List<User> users = new ArrayList<>();
        userRepository.findAll().iterator().forEachRemaining(users ::add);

        return users;
    }

    @Override
    public User findUserById(long id) {

        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User "+id+" Not Found"));
    }

    @Override
    public void deleteUserById(long id) {
        if (userRepository.findById(id).isPresent()) {
            userRepository.deleteUserByUserid(id);
        } else {
            throw new EntityNotFoundException("User "+id+" Not Found");
        }
    }

    @Transactional
    @Override
    public User save(User user) {

        User newUser = new User();

        if (user.getUserid() != 0) {
            User oldUser = userRepository.findById(user.getUserid()).orElseThrow(() -> new EntityNotFoundException("User "+user.getUserid()+" Not Found"));

            for (UserTodos userTodo : oldUser.getTodos()) {
                deleteUserTodo(userTodo.getUser().getUserid(), userTodo.getTodo().getTodoid());
            }
            newUser.setUserid(user.getUserid());
        }

        newUser.setUsername(user.getUsername());
        newUser.setPassword(user.getPassword());
        newUser.setPrimaryemail(user.getPrimaryemail());

        newUser.getTodos().clear();
        if (user.getUserid() == 0 ) {
            for (UserTodos userTodo : user.getTodos()) {
                Todo newTodo = todoService.findTodoById(userTodo.getTodo().getTodoid());

                newUser.addTodo(newTodo);
            }
        } else {
            for (UserTodos userTodo : user.getTodos()) {
                addUserTodo(newUser.getUserid(), userTodo.getTodo().getTodoid());
            }
        }

        return userRepository.save(newUser);
    }

    @Transactional
    @Override
    public void deleteUserTodo(long userid, long todoid) {
        userRepository.findById(userid).orElseThrow(() -> new EntityNotFoundException("User "+userid+" Not Found"));
        todoService.findTodoById(todoid);

        if (userRepository.checkUserTodosCombo(userid, todoid).getCount() > 0 ) {
            userRepository.deleteUserTodos(userid, todoid);
        } else {
            throw new EntityNotFoundException("User and Todo Combination Does Not Exist");
        }
    }

    @Transactional
    @Override
    public void addUserTodo(long userid, long todoid) {
        userRepository.findById(userid).orElseThrow(() -> new EntityNotFoundException("User "+userid+" Not Found"));
        todoService.findTodoById(todoid);

        if (userRepository.checkUserTodosCombo(userid, todoid).getCount() <= 0 ) {
            userRepository.insertUserTodos( userid, todoid, userAuditing.getCurrentAuditor().get());
        } else {
            throw new EntityExistsException("User and Todo Combination Already Exists");
        }
    }

    @Override
    public List<TodoCounts> getTodoCountsByUser() {

        return userRepository.getTodoCountsByUser();
    }
}
