package example.adapter.secondary.persistence.jdbc.todo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Query;

import example.domain.todo.NewTodo;
import example.domain.todo.Todo;
import example.domain.todo.TodoFilter;
import example.domain.todo.TodoRepository;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface JdbcTodoRepository extends TodoRepository, Repository<Todo, Long> {
    @Override
    Optional<Todo> findById(Long id);

    @Override
    List<Todo> findAll();

    @Override
    default List<Todo> filterBy(TodoFilter filter) {
        switch (filter) {
            case Completed:
                return filterByCompleted(true);
            case Active:
                return filterByCompleted(false);
            default:
                return findAll();
        }
    }

    @Query("SELECT * FROM todo WHERE completed = :completed")
    List<Todo> filterByCompleted(@Param("completed") Boolean completed);

    @Override
    default Todo save(NewTodo todo) {
        return save(Todo.builder()
                .id(null)
                .title(todo.getTitle())
                .desc(todo.getDesc())
                .completed(todo.getCompleted())
                .build());
    }

    @Override
    Todo save(Todo save);

    @Override
    default void remove(Long id) {
        deleteById(id);
    }

    void deleteById(Long id);
}
