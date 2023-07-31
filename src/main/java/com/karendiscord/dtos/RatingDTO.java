package com.karendiscord.dtos;

import com.karendiscord.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RatingDTO {
    private int stars;
    private int ownerId;

}
