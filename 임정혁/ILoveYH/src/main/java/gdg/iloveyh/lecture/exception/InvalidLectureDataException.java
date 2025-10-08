package gdg.iloveyh.lecture.exception;

public class InvalidLectureDataException extends RuntimeException {
    
    public InvalidLectureDataException() {
        super("잘못된 강의 데이터라는데요?");
    }
    
    public InvalidLectureDataException(String message) {
        super(message);
    }
    
    public InvalidLectureDataException(String message, Throwable cause) {
        super(message, cause);
    }
}
