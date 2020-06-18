package local.jren.todo.services;

import local.jren.todo.models.Todos;
import local.jren.todo.models.User;
import local.jren.todo.repositories.TodosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service(value = "todosService")
public class TodosServiceImpl implements TodosService{

    @Autowired
    private TodosRepository todosRepository;

    @Override
    public Todos findTodoById(long id) {
        return todosRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Todo "+id+" Not Found"));
    }

    @Transactional
    @Override
    public Todos save(Todos todos, User user) {

        Todos newTodos = new Todos(user, todos.getDescription());

        return todosRepository.save(newTodos);
    }

    @Transactional
    @Override
    public Todos markComplete(Todos todos) {

        if (todosRepository.findById(todos.getTodoid()).isPresent()) {
            todos.setCompleted(true);
        } else {
            throw new EntityNotFoundException("Todo "+todos.getTodoid()+" Not Found");
        }

        return todosRepository.save(todos);
    }
}
