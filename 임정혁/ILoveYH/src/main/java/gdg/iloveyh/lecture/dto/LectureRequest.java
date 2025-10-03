package gdg.iloveyh.lecture.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LectureRequest {
    
    @NotBlank(message = "강의 제목은 필수입니다")
    @Size(max = 100, message = "강의 제목은 100자를 초과할 수 없습니다")
    private String title;
    
    @NotBlank(message = "강의 설명은 필수입니다")
    @Size(max = 1000, message = "강의 설명은 1000자를 초과할 수 없습니다")
    private String description;
    
    @NotNull(message = "강의 가격은 필수입니다")
    @Min(value = 0, message = "강의 가격은 0원 이상이어야 합니다")
    private Long price;
}
