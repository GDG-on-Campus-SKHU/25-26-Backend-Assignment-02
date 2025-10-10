package gdg.restapi.domain;

public class Book {

    private Long id;
    private String title;
    private String author;

    public Book(Long id, String title, String author) {
        if ((title == null || title.isBlank()) || (author == null || author.isBlank())) {
            throw new IllegalArgumentException("잘못된 입력입니다.");
        }

        this.id = id;
        this.title = title;
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
