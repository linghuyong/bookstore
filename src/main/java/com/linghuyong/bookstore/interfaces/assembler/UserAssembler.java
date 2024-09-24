package com.linghuyong.bookstore.interfaces.assembler;

import com.linghuyong.bookstore.domain.user.entity.User;
import com.linghuyong.bookstore.interfaces.dto.UserDTO;

public class UserAssembler {
    public static UserDTO toDTO(User user){
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());

        return dto;
    }

    public static User toDO(UserDTO dto){
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());

        return user;
    }
}
