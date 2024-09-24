package com.linghuyong.bookstore.interfaces.assembler;

import com.linghuyong.bookstore.domain.book.entity.Author;
import com.linghuyong.bookstore.interfaces.dto.AuthorDTO;

public class AuthorAssembler {
    public static AuthorDTO toDTO(Author author){
        AuthorDTO dto = new AuthorDTO();
        dto.setId(author.getId());
        dto.setName(author.getName());
        return dto;
    }

    public static Author toDO(AuthorDTO dto){
        Author author = new Author();
        author.setId(dto.getId());
        author.setName(dto.getName());
        return author;
    }
}
