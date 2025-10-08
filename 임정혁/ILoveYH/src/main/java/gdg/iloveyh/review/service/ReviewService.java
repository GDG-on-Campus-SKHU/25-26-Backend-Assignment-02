package gdg.iloveyh.review.service;

import gdg.iloveyh.review.domain.Review;
import gdg.iloveyh.review.dto.ReviewRequest;
import gdg.iloveyh.review.dto.ReviewResponse;
import gdg.iloveyh.review.exception.ReviewNotFoundException;
import gdg.iloveyh.review.mapper.ReviewMapper;
import gdg.iloveyh.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;

    @Transactional
    public ReviewResponse create(ReviewRequest request) {
        Review review = reviewMapper.toEntity(request);
        Review saved = reviewRepository.save(review);
        
        log.info("리뷰 생성됨 - id: {}, title: '{}', rating: {}", 
                saved.getId(), saved.getTitle(), saved.getRating());
        return reviewMapper.toResponse(saved);
    }

    public List<ReviewResponse> getAll() {
        List<Review> reviews = reviewRepository.findAll();
        
        log.debug("전체 리뷰 목록 조회됨 - 총 {}개", reviews.size());
        return reviews.stream()
                .map(reviewMapper::toResponse)
                .collect(Collectors.toList());
    }

    public ReviewResponse getById(Long id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ReviewNotFoundException(id));
        
        log.debug("리뷰 조회됨 - id: {}, title: '{}'", review.getId(), review.getTitle());
        return reviewMapper.toResponse(review);
    }

    @Transactional
    public ReviewResponse update(Long id, ReviewRequest request) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ReviewNotFoundException(id));
        
        review.update(
                request.getTitle(),
                request.getContent(),
                request.getRating(),
                request.getAuthor()
        );
        
        Review updated = reviewRepository.save(review);
        
        log.info("리뷰 수정됨 - id: {}, title: '{}'", updated.getId(), updated.getTitle());
        return reviewMapper.toResponse(updated);
    }

    @Transactional
    public void delete(Long id) {
        if (!reviewRepository.existsById(id)) {
            throw new ReviewNotFoundException(id);
        }
        
        reviewRepository.deleteById(id);
        log.info("리뷰 삭제됨 - id: {}", id);
    }

    public List<ReviewResponse> getByRating(Integer rating) {
        List<Review> reviews = reviewRepository.findByRating(rating);
        
        log.debug("평점 {} 리뷰 목록 조회됨 - 총 {}개", rating, reviews.size());
        return reviews.stream()
                .map(reviewMapper::toResponse)
                .collect(Collectors.toList());
    }

    public List<ReviewResponse> getByAuthor(String author) {
        List<Review> reviews = reviewRepository.findByAuthor(author);
        
        log.debug("작성자 '{}' 리뷰 목록 조회됨 - 총 {}개", author, reviews.size());
        return reviews.stream()
                .map(reviewMapper::toResponse)
                .collect(Collectors.toList());
    }

    public List<ReviewResponse> searchByTitle(String keyword) {
        List<Review> reviews = reviewRepository.findByTitleContaining(keyword);
        
        log.debug("제목 검색 '{}' 리뷰 목록 조회됨 - 총 {}개", keyword, reviews.size());
        return reviews.stream()
                .map(reviewMapper::toResponse)
                .collect(Collectors.toList());
    }
}

