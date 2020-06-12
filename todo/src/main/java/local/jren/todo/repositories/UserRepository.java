package local.jren.todo.repositories;

import local.jren.todo.models.User;
import local.jren.todo.views.TodoCounts;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {

    @Query(value = "SELECT username as usernamerpt,(SELECT COUNT(t.userid) FROM todos t WHERE t.userid = u.userid) as counttodos FROM users u ORDER BY usernamerpt", nativeQuery = true)
    List<TodoCounts> getTodoCountsByUser();
}
