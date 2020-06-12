package local.jren.todos.repositories;

import local.jren.todos.models.User;
import local.jren.todos.views.Count;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends CrudRepository<User, Long> {
    void deleteUserByUserid(long id);

    @Query(value = "SELECT COUNT(*) as count FROM usertodos WHERE userid = :userid AND todoid = :todoid", nativeQuery = true)
    Count checkUserTodosCombo(long userid, long todoid);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM usertodos WHERE userid = :userid AND todoid = :todoid", nativeQuery = true)
    void deleteUserTodos(long userid, long todoid);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO usertodos(userid, todoid, createdby, createddate, lastmodifiedby, lastmodifieddate) VALUES (:userid, :todoid, :uname, CURRENT_TIMESTAMP, :uname, CURRENT_TIMESTAMP);", nativeQuery = true)
    void insertUserTodos(long userid, long todoid, String uname);
}
