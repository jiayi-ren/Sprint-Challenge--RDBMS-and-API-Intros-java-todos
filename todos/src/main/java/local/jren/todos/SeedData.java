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

    @Autowired
    private TodoRepository todoRepository;

    @Transactional
    @Override
    public void run(String[] args) throws Exception
    {
        Todo t1 = new Todo();
        t1.setDescription("Give Joe access rights");
        todoRepository.save(t1);

        Todo t2 = new Todo();
        t2.setDescription("Change the color of the home page");
        todoRepository.save(t2);

        User u1 = new User("admin",
            "password",
            "admin@lambdaschool.local");
        u1.getTodos()
            .add(new UserTodos(u1,
                t1));
        u1.getTodos()
            .add(new UserTodos(u1,
                t2));

        userService.save(u1);

        Todo t3 = new Todo();
        t3.setDescription("Take a nap");
        todoRepository.save(t3);

        Todo t4 = new Todo();
        t4.setDescription("Rearrange my hutch");
        todoRepository.save(t4);

        Todo t5 = new Todo();
        t5.setDescription("Groom my fur");
        todoRepository.save(t5);

        User u2 = new User("cinnamon",
            "1234567",
            "cinnamon@lambdaschool.local");
        u2.getTodos()
            .add(new UserTodos(u2,
                t3));
        u2.getTodos()
            .add(new UserTodos(u2,
                t4));
        u2.getTodos()
            .add(new UserTodos(u2,
                t5));
        userService.save(u2);

        Todo t6 = new Todo();
        t6.setDescription("Rearrange my hutch");
        todoRepository.save(t6);

        User u3 = new User("barnbarn",
            "ILuvM4th!",
            "barnbarn@lambdaschool.local");
        u3.getTodos()
            .add(new UserTodos(u3,
                    t6));
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
