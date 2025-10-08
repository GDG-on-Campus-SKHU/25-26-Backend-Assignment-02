package gdg.restapi.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder

public class TodoRequest {
    private String title;
    private String content;
    private boolean isWeekly;
}
