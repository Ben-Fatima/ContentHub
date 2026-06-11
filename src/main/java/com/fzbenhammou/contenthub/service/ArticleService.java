package com.fzbenhammou.contenthub.service;

import com.fzbenhammou.contenthub.model.Article;
import com.fzbenhammou.contenthub.dto.ArticleRequest;
import com.fzbenhammou.contenthub.dto.ArticleResponse;
import com.fzbenhammou.contenthub.dto.ArticleListResponse;
import com.fzbenhammou.contenthub.repository.ArticleRepository;
import org.springframework.stereotype.Service;
import com.fzbenhammou.contenthub.exception.DuplicateSlugException;
import com.fzbenhammou.contenthub.exception.ArticleNotFoundException;
import java.util.List;

@Service
public class ArticleService {

    private final ArticleRepository repository;

    public ArticleService(ArticleRepository repository) 
    {
        this.repository = repository;
    }

    public ArticleResponse createArticle(ArticleRequest request)
    {

        if(repository.findBySlug(request.slug()).isPresent()) {
            throw new DuplicateSlugException("Slug must be unique");
        }

        Article article = Article.builder()
            .title(request.title())
            .slug(request.slug())
            .body(request.body())
            .excerpt(request.excerpt())
            .build();

        repository.save(article);

        return toResponse(article);
    }

    public ArticleResponse getArticleBySlug(String slug)
    {
        Article article = repository.findBySlug(slug).orElseThrow(() -> new ArticleNotFoundException("Article not found"));

        return toResponse(article);
 
    }

    public List<ArticleListResponse> getAllArticles() 
    {
        return repository.findAll().stream()
        .map(this::toListResponse)
        .toList();
    }

    public ArticleResponse updateArticle(String slug, ArticleRequest request)
    {
        Article article = repository.findBySlug(slug).orElseThrow(() -> new ArticleNotFoundException("Article not found"));

        article.setTitle(request.title());
        article.setSlug(request.slug());
        article.setBody(request.body());
        article.setExcerpt(request.excerpt());

        repository.save(article);

        return toResponse(article);
    }

    public void deleteArticle(String slug) 
    {
        Article article = repository.findBySlug(slug).orElseThrow(() -> new ArticleNotFoundException("Article not found"));
        repository.delete(article);
    }

    private ArticleResponse toResponse(Article article) 
    {
        return new ArticleResponse(
            article.getId(), 
            article.getTitle(), 
            article.getSlug(), 
            article.getBody(),
            article.getExcerpt(),
            article.getStatus(),
            article.getViewCount(),
            article.getCreatedAt(),
            article.getUpdatedAt(),
            article.getPublishedAt()
        );
    }

    private ArticleListResponse toListResponse(Article article) {
        return new ArticleListResponse(
            article.getId(),
            article.getTitle(),
            article.getSlug(),
            article.getExcerpt(),
            article.getStatus(),
            article.getViewCount(),
            article.getPublishedAt()
        );
}
}
