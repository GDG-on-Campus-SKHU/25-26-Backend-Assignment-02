package gdg.iloveyh.review.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "리뷰 응답")
public class ReviewResponse {

    @Schema(description = "리뷰 ID", example = "1")
    private Long id;

    @Schema(description = "리뷰 제목", example = "정말 좋은 강의였습니다!")
    private String title;

    @Schema(description = "리뷰 내용", example = "강의 내용이 매우 알차고 실용적이었습니다.")
    private String content;

    @Schema(description = "평점 (1-5)", example = "5")
    private Integer rating;

    @Schema(description = "작성자 이름", example = "홍길동")
    private String author;

    @Schema(description = "생성 일시", example = "2025-10-07T10:15:30")
    private LocalDateTime createdAt;

    @Schema(description = "수정 일시", example = "2025-10-07T10:15:30")
    private LocalDateTime updatedAt;
}

