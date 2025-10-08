package gdg.iloveyh.review.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "리뷰 생성 및 수정 요청")
public class ReviewRequest {

    @NotBlank(message = "제목은 필수입니다")
    @Size(max = 100, message = "제목은 100자를 초과할 수 없습니다")
    @Schema(description = "리뷰 제목", example = "정말 좋은 강의였습니다!")
    private String title;

    @NotBlank(message = "내용은 필수입니다")
    @Size(min = 10, message = "내용은 최소 10자 이상이어야 합니다")
    @Schema(description = "리뷰 내용", example = "울랄라 재밈ㅆ다.")
    private String content;

    @NotNull(message = "평점은 필수입니다")
    @Min(value = 1, message = "평점은 1점 이상이어야 합니다")
    @Max(value = 5, message = "평점은 5점 이하여야 합니다")
    @Schema(description = "평점 (1-5)", example = "5")
    private Integer rating;

    @NotBlank(message = "작성자는 필수입니다")
    @Size(max = 50, message = "작성자는 50자를 초과할 수 없습니다")
    @Schema(description = "작성자 이름", example = "홍길동")
    private String author;
}

