package local.jren.todo.controllers;

import local.jren.todo.models.User;
import local.jren.todo.services.UserService;
import local.jren.todo.views.TodoCounts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    // GET http://localhost:2019/users/users
    @GetMapping(value = "/users", produces = {"application/json"})
    public ResponseEntity<?> findAllUsers() {
        List<User> userList = userService.findAllUsers();
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    // GET http://localhost:2019/users/user/4
    @GetMapping(value = "/user/{userid}", produces = {"application/json"})
    public ResponseEntity<?> findUserById(@PathVariable long userid) {
        User user = userService.findUserById(userid);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    // GET http://localhost:2019/users/users/todos
    @GetMapping(value = "/users/todos", produces = {"application/json"})
    public ResponseEntity<?> listUsersWithTodoCounts() {
        List<TodoCounts> todoCountsList = userService.getTodoCountsByUser();
        return new ResponseEntity<>(todoCountsList, HttpStatus.OK);
    }

    // DELETE http://localhost:2019/users/user/4
    @DeleteMapping(value = "/user/{userid}")
    public ResponseEntity<?> deleteUserById(@PathVariable long userid) {
        userService.delete(userid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // POST http://localhost:2019/users/user
    @PostMapping(value = "/user", consumes = {"application/json"})
    public ResponseEntity<?> addNewUser(@Valid @RequestBody User newUser) {
        newUser.setUserid(0);
        newUser = userService.save(newUser);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newUserURI = ServletUriComponentsBuilder.fromCurrentRequest().path("/user/{userid}").buildAndExpand(newUser.getUserid()).toUri();
        responseHeaders.setLocation(newUserURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }
}
