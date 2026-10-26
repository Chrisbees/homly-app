package com.chrisbees.e_commerce.category.domain.dto;

import java.time.Instant;

public record CategoryResponse(
    String uuid,
    String name,
    String desc,
    Instant createdAt,
    Instant updatedAt
) {

}
