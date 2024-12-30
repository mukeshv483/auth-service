package com.myecom.myapp.domain.dto.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserLoginResponse {
    private String jwt;

}
