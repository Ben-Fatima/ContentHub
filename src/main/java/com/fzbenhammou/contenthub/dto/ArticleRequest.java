package com.fzbenhammou.contenthub.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;

public record ArticleRequest (
    @NotBlank @Size(min = 1, max = 255) String title,
    @NotBlank @Pattern(regexp = "^[a-z0-9]+(?:-[a-z0-9]+)*$") String slug,
    @NotBlank @Size(min = 10) String body,
    @Size(min = 1, max = 500) String excerpt
) {}
