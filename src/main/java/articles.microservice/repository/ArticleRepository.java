package articles.microservice.repository;

import articles.microservice.model.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by manju on 26-06-2020.
 */
@Repository
public interface ArticleRepository extends JpaRepository<Article,Long>{
    Page<Article> findByBlogId(Long blogId, Pageable pageable);
    Optional<Article> findByArticleIdAndBlogId(Long id, Long blogId);

}
