package local.jren.todos.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "todos")
public class Todo extends Auditable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long todoid;

//    @Column(nullable = false)
    private String description;

    private boolean completed = false;

    @ManyToOne
    @JoinColumn(name = "userid", nullable = false)
    @JsonIgnoreProperties(value = "todos")
    private User user;

    @OneToMany(mappedBy = "todo", cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = "todo")
    private List<UserTodos> todos = new ArrayList<>();

    public Todo() {
    }

    public Todo(String description) {
        this.description = description;
    }

    public Todo(String description, User user) {
        this.description = description;
        this.user = user;
    }
//
//    public Todo(String dscription, boolean completed) {
//        this.description = description;
//        this.completed = completed;
//    }

    public long getTodoid() {
        return todoid;
    }

    public void setTodoid(long todoid) {
        this.todoid = todoid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<UserTodos> getTodos() {
        return todos;
    }

    public void setTodos(List<UserTodos> todos) {
        this.todos = todos;
    }
}
