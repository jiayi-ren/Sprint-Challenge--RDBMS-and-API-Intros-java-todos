package local.jren.todos.controllers;

import local.jren.todos.models.User;
import local.jren.todos.services.TodoService;
import local.jren.todos.services.UserService;
import local.jren.todos.views.TodoCounts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // GET http://localhost:2019/users/users
    @GetMapping(value = "/users", produces = {"application/json"})
    public ResponseEntity<?> findAllUsers() {
        List<User> users = userService.findAllUsers();

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    // GET http://localhost:2019/users/user/3
    @GetMapping(value = "/user/{userid}", produces = {"application/json"})
    public ResponseEntity<?> findUserById(@PathVariable long userid) {
        User user = userService.findUserById(userid);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    // GET http://localhost:2019/users/users/todos
    @GetMapping(value = "/users/todos", produces = {"application/json"})
    public ResponseEntity<?> listUsersWithTodoCounts() {
        List<TodoCounts> counts = userService.getTodoCountsByUser();

        return new ResponseEntity<>(counts, HttpStatus.OK);
    }

    // DELETE http://localhost:2019/users/user/3
    @DeleteMapping(value = "/user/{userid}")
    public ResponseEntity<?> deleteUserById(@PathVariable long userid) {
        userService.deleteUserById(userid);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
