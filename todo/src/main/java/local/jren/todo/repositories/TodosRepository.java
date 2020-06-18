package local.jren.todo.repositories;

import local.jren.todo.models.Todos;
import org.springframework.data.repository.CrudRepository;

public interface TodosRepository extends CrudRepository<Todos,Long> {
}
