package gdg.restapi.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder

public class TodoResponse {
    private Long id;
    private String todoTitle;
    private String todoContent;
    private boolean isWeekly;
}
