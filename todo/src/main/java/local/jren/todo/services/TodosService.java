package local.jren.todo.services;

import local.jren.todo.models.Todos;
import local.jren.todo.models.User;

public interface TodosService {
    Todos findTodoById(long id);
    Todos save(Todos todos, User user);
    Todos markComplete(Todos todos);
}
