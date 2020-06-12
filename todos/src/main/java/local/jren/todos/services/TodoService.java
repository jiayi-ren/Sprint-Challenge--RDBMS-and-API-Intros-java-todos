package local.jren.todos.services;

import local.jren.todos.models.Todo;

public interface TodoService {

    Todo findTodoById(long id);
    Todo getTodoCounts();
}
