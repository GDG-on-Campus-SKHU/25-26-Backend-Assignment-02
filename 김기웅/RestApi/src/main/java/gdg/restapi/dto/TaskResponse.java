package gdg.restapi.dto;

import gdg.restapi.domain.Task;

public class TaskResponse {
    private final Long id;
    private final String title;
    private final String content;

    public TaskResponse(final Task task) {
        this.id = task.getId();
        this.title = task.getTitle();
        this.content = task.getContent();
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getContent() { return content; }
}
