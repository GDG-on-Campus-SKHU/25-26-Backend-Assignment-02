package gdg.iloveyh.review.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import gdg.iloveyh.review.dto.ReviewRequest;
import gdg.iloveyh.review.dto.ReviewResponse;
import gdg.iloveyh.review.exception.ReviewNotFoundException;
import gdg.iloveyh.review.service.ReviewService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ReviewController.class)
@DisplayName("ReviewController 단위 테스트")
class ReviewControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private ReviewService reviewService;

    private ReviewRequest reviewRequest;
    private ReviewResponse reviewResponse;

    @BeforeEach
    void setUp() {
        reviewRequest = ReviewRequest.builder()
                .title("말도안되는 강의")
                .content("영한갓 영한갓 외쳐 영한갓")
                .rating(5)
                .author("김0한")
                .build();

        reviewResponse = ReviewResponse.builder()
                .id(1L)
                .title("ㄴㄹ하ㅣ룬아ㅣㅎ")
                .content("ㅇ우에에에")
                .rating(5)
                .author("김1한")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    @Test
    @DisplayName("POST /api/reviews - 리뷰 생성 성공")
    void createReview_Success() throws Exception {
        // given
        when(reviewService.create(any(ReviewRequest.class))).thenReturn(reviewResponse);

        // when & then
        mockMvc.perform(post("/api/reviews")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(reviewRequest)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.title").value("훌륭한 강의"))
                .andExpect(jsonPath("$.rating").value(5))
                .andExpect(jsonPath("$.author").value("홍길동"));

        verify(reviewService, times(1)).create(any(ReviewRequest.class));
    }

    @Test
    @DisplayName("POST /api/reviews - 유효성 검증 실패 (제목 누락)")
    void createReview_ValidationFail_NoTitle() throws Exception {
        // given
        ReviewRequest invalidRequest = ReviewRequest.builder()
                .content("내용입니다")
                .rating(5)
                .author("홍길동")
                .build();

        // when & then
        mockMvc.perform(post("/api/reviews")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidRequest)))
                .andDo(print())
                .andExpect(status().isBadRequest());

        verify(reviewService, never()).create(any(ReviewRequest.class));
    }

    @Test
    @DisplayName("POST /api/reviews - 유효성 검증 실패 (평점 범위 초과)")
    void createReview_ValidationFail_InvalidRating() throws Exception {
        // given
        ReviewRequest invalidRequest = ReviewRequest.builder()
                .title("제목")
                .content("내용입니다만 최소 10자 이상이어야 합니다")
                .rating(6)  // 1-5 범위 초과
                .author("홍길동")
                .build();

        // when & then
        mockMvc.perform(post("/api/reviews")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidRequest)))
                .andDo(print())
                .andExpect(status().isBadRequest());

        verify(reviewService, never()).create(any(ReviewRequest.class));
    }

    @Test
    @DisplayName("GET /api/reviews - 전체 리뷰 조회 성공")
    void getAllReviews_Success() throws Exception {
        // given
        ReviewResponse response2 = ReviewResponse.builder()
                .id(2L)
                .title("좋은 강의")
                .content("추천합니다")
                .rating(4)
                .author("김철수")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        List<ReviewResponse> reviews = Arrays.asList(reviewResponse, response2);
        when(reviewService.getAll()).thenReturn(reviews);

        // when & then
        mockMvc.perform(get("/api/reviews"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].title").value("훌륭한 강의"))
                .andExpect(jsonPath("$[1].title").value("좋은 강의"));

        verify(reviewService, times(1)).getAll();
    }

    @Test
    @DisplayName("GET /api/reviews/{id} - 리뷰 상세 조회 성공")
    void getReview_Success() throws Exception {
        // given
        when(reviewService.getById(1L)).thenReturn(reviewResponse);

        // when & then
        mockMvc.perform(get("/api/reviews/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.title").value("훌륭한 강의"))
                .andExpect(jsonPath("$.rating").value(5));

        verify(reviewService, times(1)).getById(1L);
    }

    @Test
    @DisplayName("GET /api/reviews/{id} - 존재하지 않는 리뷰 조회")
    void getReview_NotFound() throws Exception {
        // given
        when(reviewService.getById(999L)).thenThrow(new ReviewNotFoundException(999L));

        // when & then
        mockMvc.perform(get("/api/reviews/999"))
                .andDo(print())
                .andExpect(status().isNotFound());

        verify(reviewService, times(1)).getById(999L);
    }

    @Test
    @DisplayName("PUT /api/reviews/{id} - 리뷰 수정 성공")
    void updateReview_Success() throws Exception {
        // given
        ReviewResponse updatedResponse = ReviewResponse.builder()
                .id(1L)
                .title("수정된 제목")
                .content("수정된 내용입니다. 더 자세히 설명합니다.")
                .rating(4)
                .author("홍길동")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        when(reviewService.update(eq(1L), any(ReviewRequest.class))).thenReturn(updatedResponse);

        ReviewRequest updateRequest = ReviewRequest.builder()
                .title("수정된 제목")
                .content("수정된 내용입니다. 더 자세히 설명합니다.")
                .rating(4)
                .author("홍길동")
                .build();

        // when & then
        mockMvc.perform(put("/api/reviews/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateRequest)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("수정된 제목"))
                .andExpect(jsonPath("$.rating").value(4));

        verify(reviewService, times(1)).update(eq(1L), any(ReviewRequest.class));
    }

    @Test
    @DisplayName("PUT /api/reviews/{id} - 존재하지 않는 리뷰 수정")
    void updateReview_NotFound() throws Exception {
        // given
        when(reviewService.update(eq(999L), any(ReviewRequest.class)))
                .thenThrow(new ReviewNotFoundException(999L));

        // when & then
        mockMvc.perform(put("/api/reviews/999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(reviewRequest)))
                .andDo(print())
                .andExpect(status().isNotFound());

        verify(reviewService, times(1)).update(eq(999L), any(ReviewRequest.class));
    }

    @Test
    @DisplayName("DELETE /api/reviews/{id} - 리뷰 삭제 성공")
    void deleteReview_Success() throws Exception {
        // given
        doNothing().when(reviewService).delete(1L);

        // when & then
        mockMvc.perform(delete("/api/reviews/1"))
                .andDo(print())
                .andExpect(status().isNoContent());

        verify(reviewService, times(1)).delete(1L);
    }

    @Test
    @DisplayName("DELETE /api/reviews/{id} - 존재하지 않는 리뷰 삭제")
    void deleteReview_NotFound() throws Exception {
        // given
        doThrow(new ReviewNotFoundException(999L)).when(reviewService).delete(999L);

        // when & then
        mockMvc.perform(delete("/api/reviews/999"))
                .andDo(print())
                .andExpect(status().isNotFound());

        verify(reviewService, times(1)).delete(999L);
    }

    @Test
    @DisplayName("GET /api/reviews/rating/{rating} - 평점별 리뷰 조회 성공")
    void getReviewsByRating_Success() throws Exception {
        // given
        when(reviewService.getByRating(5)).thenReturn(Arrays.asList(reviewResponse));

        // when & then
        mockMvc.perform(get("/api/reviews/rating/5"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].rating").value(5));

        verify(reviewService, times(1)).getByRating(5);
    }

    @Test
    @DisplayName("GET /api/reviews/author/{author} - 작성자별 리뷰 조회 성공")
    void getReviewsByAuthor_Success() throws Exception {
        // given
        when(reviewService.getByAuthor("홍길동")).thenReturn(Arrays.asList(reviewResponse));

        // when & then
        mockMvc.perform(get("/api/reviews/author/홍길동"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].author").value("홍길동"));

        verify(reviewService, times(1)).getByAuthor("홍길동");
    }

    @Test
    @DisplayName("GET /api/reviews/search?keyword={keyword} - 제목 검색 성공")
    void searchReviewsByTitle_Success() throws Exception {
        // given
        when(reviewService.searchByTitle("강의")).thenReturn(Arrays.asList(reviewResponse));

        // when & then
        mockMvc.perform(get("/api/reviews/search")
                        .param("keyword", "강의"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].title", containsString("강의")));

        verify(reviewService, times(1)).searchByTitle("강의");
    }
}

