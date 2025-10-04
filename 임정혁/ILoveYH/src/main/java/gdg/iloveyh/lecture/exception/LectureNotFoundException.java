package gdg.iloveyh.lecture.exception;

public class LectureNotFoundException extends RuntimeException {
    
    public LectureNotFoundException() {
        super("영한이 형님께서 그런 강의를 낸 적 없어..!!");
    }
    
    public LectureNotFoundException(String message) {
        super(message);
    }
    
    public LectureNotFoundException(Long id) {
        super(id + " 번 강의를 찾을 수 없어용");
    }
    
    public LectureNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
