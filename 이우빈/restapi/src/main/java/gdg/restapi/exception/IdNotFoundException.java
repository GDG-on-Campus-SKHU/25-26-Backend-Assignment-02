package gdg.restapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class IdNotFoundException extends RuntimeException {

    public IdNotFoundException(Long id) {
        super("존재하지 않는 ID입니다.");
    }
}
