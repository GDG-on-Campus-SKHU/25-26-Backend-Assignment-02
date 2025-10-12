package gdg.restapi.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Todo {
    private Long id;
    private String title;
    private String content;
    private boolean isWeekly;

    public void id(Long id){
        this.id = id;
    }
}
