package gdg.restapi.dto;

import lombok.Getter;
import lombok.AllArgsConstructor;

@Getter
@AllArgsConstructor

public class NoteResponseDto {
    private final Long id;
    private final String content;
}
