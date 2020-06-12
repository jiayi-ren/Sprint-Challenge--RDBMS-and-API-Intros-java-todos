package local.jren.todos;

import local.jren.todos.models.Todo;
import local.jren.todos.models.User;
import local.jren.todos.models.UserTodos;
import local.jren.todos.repositories.TodoRepository;
import local.jren.todos.repositories.UserRepository;
import local.jren.todos.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Component
public class SeedData implements CommandLineRunner
{
    @Autowired
    UserService userService;

//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private TodoRepository todoRepository;

    @Transactional
    @Override
    public void run(String[] args) throws Exception
    {
//        Todo t1 = new Todo();
//        t1.setDescription("Give Joe access rights");
//        todoRepository.save(t1);
//
//        Todo t2 = new Todo();
//        t2.setDescription("Change the color of the home page");
//        todoRepository.save(t2);
//
//        User u1 = new User("admin",
//                "password",
//                "admin@lambdaschool.local");
//        userRepository.save(u1);
//
//        List<UserTodos> userTodosList = new ArrayList<>();
//        userTodosList.add(new UserTodos(u1, t1));
//        userTodosList.add(new UserTodos(u1, t2));
//
//        Todo t3 = new Todo();
//        t3.setDescription("Take a nap");
//        todoRepository.save(t3);
//
//        Todo t4 = new Todo();
//        t4.setDescription("Rearrange my hutch");
//        todoRepository.save(t4);
//
//        Todo t5 = new Todo();
//        t5.setDescription("Groom my fur");
//        todoRepository.save(t5);
//
//        User u2 = new User("cinnamon",
//                "1234567",
//                "cinnamon@lambdaschool.local");
//        userRepository.save(u2);
//
//        userTodosList = new ArrayList<>();
//        userTodosList.add(new UserTodos(u2, t3));
//        userTodosList.add(new UserTodos(u2, t4));
//        userTodosList.add(new UserTodos(u2, t5));
//
//        Todo t6 = new Todo();
//        t6.setDescription("Rearrange my hutch");
//        todoRepository.save(t6);
//
//        User u3 = new User("barnbarn",
//                "ILuvM4th!",
//                "barnbarn@lambdaschool.local");
//        userRepository.save(u3);
//
//        userTodosList = new ArrayList<>();
//        userTodosList.add(new UserTodos(u3, t6));
//
//
//        User u4 = new User("puttat",
//                "password",
//                "puttat@school.lambda");
//        userRepository.save(u4);
//
//        User u5 = new User("misskitty",
//                "password",
//                "misskitty@school.lambda");
//        userRepository.save(u5);

        User u1 = new User("admin",
            "password",
            "admin@lambdaschool.local");
        u1.getTodos()
            .add(new UserTodos(u1,
                new Todo("Give Joe access rights", u1)));
        u1.getTodos()
            .add(new UserTodos(u1,
                new Todo("Change the color of the home page", u1)));

        userService.save(u1);

        User u2 = new User("cinnamon",
            "1234567",
            "cinnamon@lambdaschool.local");
//        u2.getTodos()
//            .add(new Todo(
//                "Take a nap", u2));
//        u2.getTodos()
//            .add(new Todo(
//                "Rearrange my hutch", u2));
//        u2.getTodos()
//            .add(new Todo(
//                "Groom my fur", u2));
        userService.save(u2);

        User u3 = new User("barnbarn",
            "ILuvM4th!",
            "barnbarn@lambdaschool.local");
//        u3.getTodos()
//            .add(new Todo(
//                "Rearrange my hutch", u3));
        userService.save(u3);

        User u4 = new User("puttat",
            "password",
            "puttat@school.lambda");
        userService.save(u4);

        User u5 = new User("misskitty",
            "password",
            "misskitty@school.lambda");
        userService.save(u5);
    }
}
