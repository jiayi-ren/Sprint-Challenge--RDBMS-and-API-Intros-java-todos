package local.jren.todos.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User extends Auditable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userid;

//    @Column(nullable = false, unique = true)
    private String username;

//    @Column(nullable = false, unique = true)
    private String primaryemail;

//    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = "user", allowSetters = true)
    private List<UserTodos> todos = new ArrayList<>();

    public User() {
    }

    public User(String username, String password, String primaryemail) {
        this.username = username;
        this.primaryemail = primaryemail;
        this.password = password;
    }

    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

    public String getUsername() {
        if (username == null) {
            return null;
        } else {
            return username.toLowerCase();
        }
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPrimaryemail() {
        if (primaryemail == null) {
            return null;
        } else {
            return primaryemail.toLowerCase();
        }
    }

    public void setPrimaryemail(String primaryemail) {
        this.primaryemail = primaryemail;
    }

    public String getPassword() {
        return password.toLowerCase();
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<UserTodos> getTodos() {
        return todos;
    }

    public void setTodos(List<UserTodos> todos) {
        this.todos = todos;
    }

    public void addTodo(Todo todo) { todos.add(new UserTodos(this, todo));}
}
