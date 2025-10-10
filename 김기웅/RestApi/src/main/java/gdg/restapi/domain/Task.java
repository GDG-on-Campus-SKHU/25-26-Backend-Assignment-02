package gdg.restapi.domain;

public class Task {
    private final Long id;
    private String title;
    private String content;

    private Task(final Long id, final String title, final String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public static Task of(final Long id, final String title, final String content) {
        return new Task(id, title, content);
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getContent() { return content; }

    public void changeTitle(final String title) {
        this.title = title;
    }

    public void changeContent(final String content) {
        this.content = content;
    }
}
