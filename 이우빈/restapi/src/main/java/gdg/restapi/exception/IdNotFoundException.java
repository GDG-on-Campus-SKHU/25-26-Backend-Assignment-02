package gdg.restapi.exception;

public class IdNotFoundException extends RuntimeException {

    public IdNotFoundException(Long id) {
        super("존재하지 않는 ID입니다.");
    }
}
