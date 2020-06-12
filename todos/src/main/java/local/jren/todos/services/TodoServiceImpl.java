package local.jren.todos.services;

import local.jren.todos.models.Todo;
import local.jren.todos.repositories.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service(value = "todoService")
public class TodoServiceImpl implements TodoService{

    @Autowired
    private TodoRepository todoRepository;

    @Override
    public Todo findTodoById(long id) {
        return todoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Todo "+id+" Not Found"));
    }

    @Override
    public Todo getTodoCounts() {
        return null;
    }
}
