package gdg.iloveyh.review.service;

import gdg.iloveyh.review.domain.Review;
import gdg.iloveyh.review.dto.ReviewRequest;
import gdg.iloveyh.review.dto.ReviewResponse;
import gdg.iloveyh.review.exception.ReviewNotFoundException;
import gdg.iloveyh.review.mapper.ReviewMapper;
import gdg.iloveyh.review.repository.ReviewRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("ReviewService 단위 테스트")
class ReviewServiceTest {

    @Mock
    private ReviewRepository reviewRepository;

    @Mock
    private ReviewMapper reviewMapper;

    @InjectMocks
    private ReviewService reviewService;

    private ReviewRequest reviewRequest;
    private Review review;
    private ReviewResponse reviewResponse;

    @BeforeEach
    void setUp() {
        reviewRequest = ReviewRequest.builder()
                .title("훌륭한 강의")
                .content("정말 유익한 내용이었습니다. 강사님의 설명이 명확했습니다.")
                .rating(5)
                .author("홍길동")
                .build();

        review = Review.builder()
                .id(1L)
                .title("훌륭한 강의")
                .content("정말 유익한 내용이었습니다. 강사님의 설명이 명확했습니다.")
                .rating(5)
                .author("홍길동")
                .build();

        reviewResponse = ReviewResponse.builder()
                .id(1L)
                .title("훌륭한 강의")
                .content("정말 유익한 내용이었습니다. 강사님의 설명이 명확했습니다.")
                .rating(5)
                .author("홍길동")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    @Test
    @DisplayName("리뷰 생성 성공")
    void createReview_Success() {
        // given
        when(reviewMapper.toEntity(reviewRequest)).thenReturn(review);
        when(reviewRepository.save(review)).thenReturn(review);
        when(reviewMapper.toResponse(review)).thenReturn(reviewResponse);

        // when
        ReviewResponse result = reviewService.create(reviewRequest);

        // then
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getTitle()).isEqualTo("훌륭한 강의");
        assertThat(result.getRating()).isEqualTo(5);
        verify(reviewRepository, times(1)).save(any(Review.class));
    }

    @Test
    @DisplayName("전체 리뷰 조회 성공")
    void getAllReviews_Success() {
        // given
        Review review2 = Review.builder()
                .id(2L)
                .title("좋은 강의")
                .content("추천합니다")
                .rating(4)
                .author("김철수")
                .build();

        ReviewResponse response2 = ReviewResponse.builder()
                .id(2L)
                .title("좋은 강의")
                .content("추천합니다")
                .rating(4)
                .author("김철수")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        when(reviewRepository.findAll()).thenReturn(Arrays.asList(review, review2));
        when(reviewMapper.toResponse(review)).thenReturn(reviewResponse);
        when(reviewMapper.toResponse(review2)).thenReturn(response2);

        // when
        List<ReviewResponse> results = reviewService.getAll();

        // then
        assertThat(results).hasSize(2);
        assertThat(results.get(0).getTitle()).isEqualTo("훌륭한 강의");
        assertThat(results.get(1).getTitle()).isEqualTo("좋은 강의");
        verify(reviewRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("ID로 리뷰 조회 성공")
    void getReviewById_Success() {
        // given
        when(reviewRepository.findById(1L)).thenReturn(Optional.of(review));
        when(reviewMapper.toResponse(review)).thenReturn(reviewResponse);

        // when
        ReviewResponse result = reviewService.getById(1L);

        // then
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getTitle()).isEqualTo("훌륭한 강의");
        verify(reviewRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("존재하지 않는 리뷰 조회 시 예외 발생")
    void getReviewById_NotFound() {
        // given
        when(reviewRepository.findById(999L)).thenReturn(Optional.empty());

        // when & then
        assertThatThrownBy(() -> reviewService.getById(999L))
                .isInstanceOf(ReviewNotFoundException.class)
                .hasMessageContaining("리뷰를 찾을 수 없습니다");
        verify(reviewRepository, times(1)).findById(999L);
    }

    @Test
    @DisplayName("리뷰 수정 성공")
    void updateReview_Success() {
        // given
        ReviewRequest updateRequest = ReviewRequest.builder()
                .title("수정된 제목")
                .content("수정된 내용")
                .rating(4)
                .author("홍길동")
                .build();

        Review updatedReview = Review.builder()
                .id(1L)
                .title("수정된 제목")
                .content("수정된 내용")
                .rating(4)
                .author("홍길동")
                .build();

        ReviewResponse updatedResponse = ReviewResponse.builder()
                .id(1L)
                .title("수정된 제목")
                .content("수정된 내용")
                .rating(4)
                .author("홍길동")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        when(reviewRepository.findById(1L)).thenReturn(Optional.of(review));
        when(reviewRepository.save(review)).thenReturn(updatedReview);
        when(reviewMapper.toResponse(updatedReview)).thenReturn(updatedResponse);

        // when
        ReviewResponse result = reviewService.update(1L, updateRequest);

        // then
        assertThat(result).isNotNull();
        assertThat(result.getTitle()).isEqualTo("수정된 제목");
        assertThat(result.getRating()).isEqualTo(4);
        verify(reviewRepository, times(1)).findById(1L);
        verify(reviewRepository, times(1)).save(any(Review.class));
    }

    @Test
    @DisplayName("존재하지 않는 리뷰 수정 시 예외 발생")
    void updateReview_NotFound() {
        // given
        when(reviewRepository.findById(999L)).thenReturn(Optional.empty());

        // when & then
        assertThatThrownBy(() -> reviewService.update(999L, reviewRequest))
                .isInstanceOf(ReviewNotFoundException.class)
                .hasMessageContaining("리뷰를 찾을 수 없습니다");
        verify(reviewRepository, times(1)).findById(999L);
        verify(reviewRepository, never()).save(any(Review.class));
    }

    @Test
    @DisplayName("리뷰 삭제 성공")
    void deleteReview_Success() {
        // given
        when(reviewRepository.existsById(1L)).thenReturn(true);
        doNothing().when(reviewRepository).deleteById(1L);

        // when
        reviewService.delete(1L);

        // then
        verify(reviewRepository, times(1)).existsById(1L);
        verify(reviewRepository, times(1)).deleteById(1L);
    }

    @Test
    @DisplayName("존재하지 않는 리뷰 삭제 시 예외 발생")
    void deleteReview_NotFound() {
        // given
        when(reviewRepository.existsById(999L)).thenReturn(false);

        // when & then
        assertThatThrownBy(() -> reviewService.delete(999L))
                .isInstanceOf(ReviewNotFoundException.class)
                .hasMessageContaining("리뷰를 찾을 수 없습니다");
        verify(reviewRepository, times(1)).existsById(999L);
        verify(reviewRepository, never()).deleteById(anyLong());
    }

    @Test
    @DisplayName("평점별 리뷰 조회 성공")
    void getReviewsByRating_Success() {
        // given
        when(reviewRepository.findByRating(5)).thenReturn(Arrays.asList(review));
        when(reviewMapper.toResponse(review)).thenReturn(reviewResponse);

        // when
        List<ReviewResponse> results = reviewService.getByRating(5);

        // then
        assertThat(results).hasSize(1);
        assertThat(results.get(0).getRating()).isEqualTo(5);
        verify(reviewRepository, times(1)).findByRating(5);
    }

    @Test
    @DisplayName("작성자별 리뷰 조회 성공")
    void getReviewsByAuthor_Success() {
        // given
        when(reviewRepository.findByAuthor("홍길동")).thenReturn(Arrays.asList(review));
        when(reviewMapper.toResponse(review)).thenReturn(reviewResponse);

        // when
        List<ReviewResponse> results = reviewService.getByAuthor("홍길동");

        // then
        assertThat(results).hasSize(1);
        assertThat(results.get(0).getAuthor()).isEqualTo("홍길동");
        verify(reviewRepository, times(1)).findByAuthor("홍길동");
    }

    @Test
    @DisplayName("제목 검색 성공")
    void searchReviewsByTitle_Success() {
        // given
        when(reviewRepository.findByTitleContaining("강의")).thenReturn(Arrays.asList(review));
        when(reviewMapper.toResponse(review)).thenReturn(reviewResponse);

        // when
        List<ReviewResponse> results = reviewService.searchByTitle("강의");

        // then
        assertThat(results).hasSize(1);
        assertThat(results.get(0).getTitle()).contains("강의");
        verify(reviewRepository, times(1)).findByTitleContaining("강의");
    }
}

