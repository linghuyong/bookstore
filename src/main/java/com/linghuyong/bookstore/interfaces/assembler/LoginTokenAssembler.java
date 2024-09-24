package com.linghuyong.bookstore.interfaces.assembler;

import com.linghuyong.bookstore.domain.security.entity.JWTToken;
import com.linghuyong.bookstore.interfaces.dto.LoginTokenDTO;

public class LoginTokenAssembler {
    public static LoginTokenDTO toDTO(JWTToken token) {
        LoginTokenDTO dto = new LoginTokenDTO();
        dto.setToken(token.getToken());
        dto.setExpireTimestamp(token.getExpireAt());

        return dto;
    }
}
