package example.domain.todo;

import lombok.*;
import org.springframework.data.annotation.Id;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@With
public class Todo {
    @Id
    private Long id;
    private String title;
    private String desc;
    private Boolean completed;

    public Todo changeTitle(String title) {
        return withTitle(title);
    }

    public Todo changeDesc(String desc) {
        return withDesc(desc);
    }

    public Todo toggleTodo() {
        return withCompleted(!completed);
    }

    public Boolean isActive() {
        return !completed;
    }

    public Boolean isCompleted() {
        return completed;
    }
}
