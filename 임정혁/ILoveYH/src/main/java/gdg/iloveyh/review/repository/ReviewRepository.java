package gdg.iloveyh.review.repository;

import gdg.iloveyh.review.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    
    // 평점별 조회
    List<Review> findByRating(Integer rating);
    
    // 작성자별 조회
    List<Review> findByAuthor(String author);
    
    // 제목으로 검색
    List<Review> findByTitleContaining(String title);
}

