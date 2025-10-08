package gdg.iloveyh.review.mapper;

import gdg.iloveyh.review.domain.Review;
import gdg.iloveyh.review.dto.ReviewRequest;
import gdg.iloveyh.review.dto.ReviewResponse;
import org.springframework.stereotype.Component;

@Component
public class ReviewMapper {

    public Review toEntity(ReviewRequest request) {
        return Review.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .rating(request.getRating())
                .author(request.getAuthor())
                .build();
    }

    public ReviewResponse toResponse(Review review) {
        return ReviewResponse.builder()
                .id(review.getId())
                .title(review.getTitle())
                .content(review.getContent())
                .rating(review.getRating())
                .author(review.getAuthor())
                .createdAt(review.getCreatedAt())
                .updatedAt(review.getUpdatedAt())
                .build();
    }
}

