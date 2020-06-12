package local.jren.todos.controllers;

import local.jren.todos.models.User;
import local.jren.todos.services.UserService;
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

    // GET http://localhost:2019/users/user
    @GetMapping(value = "/user", produces = {"application/json"})
    public ResponseEntity<?> findAllUsers() {
        List<User> users = userService.findAllUsers();

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    // GET http://localhost:2019/users/user/1
    @GetMapping(value = "/user/{userid}", produces = {"application/json"})
    public ResponseEntity<?> findUserById(@PathVariable long userid) {
        User user = userService.findUserById(userid);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    // DELETE http://localhost:2019/users/user/1
    @DeleteMapping(value = "/user/{userid}")
    public ResponseEntity<?> deleteUserById(@PathVariable long userid) {
        userService.deleteUserById(userid);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
