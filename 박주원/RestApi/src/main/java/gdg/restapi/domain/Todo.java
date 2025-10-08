package gdg.restapi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Todo {
    private Long id;
    private String title;
    private boolean completed;

    public static Todo create(String title, boolean completed) {
        return new Todo(null, title, completed);
    }

    public void update(String title, boolean completed) {
        this.title = title;
        this.completed = completed;
    }
}
