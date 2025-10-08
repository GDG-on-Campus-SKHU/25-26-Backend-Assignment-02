package gdg.iloveyh.review.controller;

import gdg.iloveyh.review.dto.ReviewRequest;
import gdg.iloveyh.review.dto.ReviewResponse;
import gdg.iloveyh.review.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
@Tag(name = "Review API", description = "리뷰 관리 API")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    @Operation(summary = "리뷰 생성", description = "새로운 리뷰를 생성합니다")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "리뷰 생성 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청 데이터")
    })
    public ResponseEntity<ReviewResponse> createReview(
            @Valid @RequestBody ReviewRequest request) {
        log.info("POST /api/reviews - 리뷰 생성 요청: {}", request.getTitle());
        ReviewResponse response = reviewService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    @Operation(summary = "리뷰 목록 조회", description = "모든 리뷰를 조회합니다")
    @ApiResponse(responseCode = "200", description = "조회 성공")
    public ResponseEntity<List<ReviewResponse>> getAllReviews() {
        log.info("GET /api/reviews - 전체 리뷰 목록 조회 요청");
        List<ReviewResponse> reviews = reviewService.getAll();
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/{id}")
    @Operation(summary = "리뷰 상세 조회", description = "ID로 특정 리뷰를 조회합니다")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공"),
            @ApiResponse(responseCode = "404", description = "리뷰를 찾을 수 없음")
    })
    public ResponseEntity<ReviewResponse> getReview(
            @Parameter(description = "리뷰 ID", example = "1")
            @PathVariable Long id) {
        log.info("GET /api/reviews/{} - 리뷰 상세 조회 요청", id);
        ReviewResponse review = reviewService.getById(id);
        return ResponseEntity.ok(review);
    }

    @PutMapping("/{id}")
    @Operation(summary = "리뷰 수정", description = "기존 리뷰를 수정합니다")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "수정 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청 데이터"),
            @ApiResponse(responseCode = "404", description = "리뷰를 찾을 수 없음")
    })
    public ResponseEntity<ReviewResponse> updateReview(
            @Parameter(description = "리뷰 ID", example = "1")
            @PathVariable Long id,
            @Valid @RequestBody ReviewRequest request) {
        log.info("PUT /api/reviews/{} - 리뷰 수정 요청", id);
        ReviewResponse response = reviewService.update(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "리뷰 삭제", description = "리뷰를 삭제합니다")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "삭제 성공"),
            @ApiResponse(responseCode = "404", description = "리뷰를 찾을 수 없음")
    })
    public ResponseEntity<Void> deleteReview(
            @Parameter(description = "리뷰 ID", example = "1")
            @PathVariable Long id) {
        log.info("DELETE /api/reviews/{} - 리뷰 삭제 요청", id);
        reviewService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/rating/{rating}")
    @Operation(summary = "평점별 리뷰 조회", description = "특정 평점의 리뷰를 조회합니다")
    @ApiResponse(responseCode = "200", description = "조회 성공")
    public ResponseEntity<List<ReviewResponse>> getReviewsByRating(
            @Parameter(description = "평점 (1-5)", example = "5")
            @PathVariable Integer rating) {
        log.info("GET /api/reviews/rating/{} - 평점별 리뷰 조회 요청", rating);
        List<ReviewResponse> reviews = reviewService.getByRating(rating);
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/author/{author}")
    @Operation(summary = "작성자별 리뷰 조회", description = "특정 작성자의 리뷰를 조회합니다")
    @ApiResponse(responseCode = "200", description = "조회 성공")
    public ResponseEntity<List<ReviewResponse>> getReviewsByAuthor(
            @Parameter(description = "작성자 이름", example = "홍길동")
            @PathVariable String author) {
        log.info("GET /api/reviews/author/{} - 작성자별 리뷰 조회 요청", author);
        List<ReviewResponse> reviews = reviewService.getByAuthor(author);
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/search")
    @Operation(summary = "리뷰 제목 검색", description = "제목에 키워드가 포함된 리뷰를 검색합니다")
    @ApiResponse(responseCode = "200", description = "검색 성공")
    public ResponseEntity<List<ReviewResponse>> searchReviewsByTitle(
            @Parameter(description = "검색 키워드", example = "강의")
            @RequestParam String keyword) {
        log.info("GET /api/reviews/search?keyword={} - 리뷰 제목 검색 요청", keyword);
        List<ReviewResponse> reviews = reviewService.searchByTitle(keyword);
        return ResponseEntity.ok(reviews);
    }
}

