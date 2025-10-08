package gdg.restapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TodoRequest {
    private String title;
    private boolean completed;
}
