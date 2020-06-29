package articles.microservice.controller;

import articles.microservice.exception.ResourceNotFoundException;
import articles.microservice.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import articles.microservice.repository.ArticleRepository;

import javax.validation.Valid;
import java.util.Optional;

/**
 * Created by manju on 28-06-2020.
 * Creater/Updates/Fetch/Deletes Articles related to blog.
 */
@RestController
public class ArticlesServiceController {

    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping("/blogs/{blogId}/articles")
    public Page<Article> getAllArticlesByBlogId(@PathVariable(value = "blogId") Long blogId,
                                                Pageable pageable) {
        return articleRepository.findByBlogId(blogId,pageable);
    }

    @PostMapping("/blogs/{blogId}/articles")
    public Article createArticle(@PathVariable (value = "blogId") Long blogId,
                                 @Valid @RequestBody Article article) {
         article.setBlogId(blogId);
        System.out.println("Request body"+article.toString());
            return articleRepository.save(article);
        //}).orElseThrow(() -> new ResourceNotFoundException("BlogId " + blogId + " not found"));
    }

    @PutMapping("/blogs/{blogId}/articles/{articleId}")
    public Article updateArticle(@PathVariable (value = "blogId") Long blogId,
                                 @PathVariable (value = "articleId") Long articleId,
                                 @Valid @RequestBody Article articleRequest) {

        return articleRepository.findById(articleId).map(article -> {
            article.setArticleName(articleRequest.getArticleName());
            return articleRepository.save(article);
        }).orElseThrow(() -> new ResourceNotFoundException("articleId " + articleId + "not found"));
    }

    @GetMapping("/blogs/{blogId}/articles/{articleId}")
    public Optional<Article> updateArticle(@PathVariable (value = "blogId") Long blogId,
                                           @PathVariable (value = "articleId") Long articleId
    ) {
        return articleRepository.findById(articleId);
    }

    @DeleteMapping("/blogs/{blogId}/articles/{articleId}")
    public ResponseEntity<?> deleteArticle(@PathVariable (value = "blogId") Long blogId,
                                           @PathVariable (value = "articleId") Long articleId) {
        return articleRepository.findByArticleIdAndBlogId(articleId, blogId).map(article -> {
            articleRepository.delete(article);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Article not found with id " + articleId + " and blogId " + blogId));
    }


}
