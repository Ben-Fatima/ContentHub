package com.fzbenhammou.contenthub.dto;
import java.time.LocalDateTime;
import com.fzbenhammou.contenthub.model.ArticleStatus;

public record ArticleResponse (
    Long id,
    String title,
    String slug,
    String body,
    String excerpt,
    ArticleStatus status,
    int viewCount,
    LocalDateTime createdAt,
    LocalDateTime updatedAt,
    LocalDateTime publishedAt
) {}
