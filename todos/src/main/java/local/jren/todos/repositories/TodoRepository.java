package local.jren.todos.repositories;

import local.jren.todos.models.Todo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface TodoRepository extends CrudRepository<Todo, Long> {

//    @Query(value = "SELECT t.todo")
}
