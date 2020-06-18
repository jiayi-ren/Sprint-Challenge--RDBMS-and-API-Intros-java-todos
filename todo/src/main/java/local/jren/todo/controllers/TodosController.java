package local.jren.todo.controllers;

import local.jren.todo.models.Todos;
import local.jren.todo.models.User;
import local.jren.todo.services.TodosService;
import local.jren.todo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/todos")
public class TodosController {

    @Autowired
    private TodosService todosService;

    @Autowired
    private UserService userService;

    // POST http://localhost:2019/todos/user/1
    @PostMapping(value = "/user/{userid}", consumes = {"application/json"})
    public ResponseEntity<?> addNewTodoByUser(@Valid @RequestBody Todos newTodo, @PathVariable long userid) {
        User user = userService.findUserById(userid);
        todosService.save(newTodo, user);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI userURI = ServletUriComponentsBuilder.fromCurrentRequest().path("/user/{userid}").buildAndExpand(user.getUserid()).toUri();
        responseHeaders.setLocation(userURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.OK);
    }

    // PATCH http://localhost:2019/todos/todo/1
    @PatchMapping(value = "todo/{todoid}")
    public ResponseEntity<?> markCompletedByTodoId(@PathVariable long todoid) {
        Todos todo = todosService.findTodoById(todoid);

        todosService.markComplete(todo);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI userURI = ServletUriComponentsBuilder.fromCurrentContextPath().path("/users/user/{userid}").buildAndExpand(todo.getUser().getUserid()).toUri();
        responseHeaders.setLocation(userURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.OK);
    }
}
