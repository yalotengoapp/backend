package com.ybytu.yalotengo.dtos;

import jakarta.validation.constraints.*;
import org.hibernate.internal.util.StringHelper;

public record ItemRequest(
        @NotBlank(message = "Title is required")
        @Size(min = 5, max = 200, message = "Title must contain between 5 and 200 characters")
        String title,

        @NotBlank(message = "Description is required")
        @Size(min = 10, max = 800, message = "Description must contain between 10 and 800 characters")
        String description,

        @NotBlank(message = "Type is required")
        @Size(min = 5, max = 200, message = "Type must contain between 5 and 200 characters")
        String type,

        @NotBlank(message = "Item condition is required")
        @Size(min = 5, max = 200, message = "Item condition must contain between 5 and 200 characters")

        String itemCondition,

        @NotBlank(message = "Image is required")
        @Pattern(regexp = "^(https?|ftp)://[a-zA-Z0-9\\-._~:/?#\\[\\]@!$&'()*+,;=.]+$",
                message = "Image URL must be a valid URL")
        String imgUrl,

        @NotBlank(message = "Points/fruits required")
        @Min(value = 1, message = "Points/fruits must be 1 or more")
        @Max(value = 5, message = "Points/fruits must be at most 5")
        String fruitsRequired
) {
}
