package local.jren.todos.repositories;

import local.jren.todos.models.Todo;
import local.jren.todos.views.TodoCounts;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TodoRepository extends CrudRepository<Todo, Long> {

}