package com.fzbenhammou.contenthub.dto;

import java.time.LocalDateTime;
import com.fzbenhammou.contenthub.model.ArticleStatus;

public record ArticleListResponse (
    Long id,
    String title,
    String slug,
    String excerpt,
    ArticleStatus status,
    int viewCount,
    LocalDateTime publishedAt
) {}

