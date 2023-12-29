package com.workcode.dto;

import com.workcode.model.UserRole;

public record RegisterDTO(String login, String password, UserRole role) {

}
