package com.karendiscord.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PasswordChangeDTO {
    private String oldPassword;
    private String newPassword;
}
