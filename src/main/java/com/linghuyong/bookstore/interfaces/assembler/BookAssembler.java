package com.linghuyong.bookstore.interfaces.assembler;

import com.linghuyong.bookstore.domain.book.entity.Author;
import com.linghuyong.bookstore.domain.book.entity.Book;
import com.linghuyong.bookstore.interfaces.dto.BookDTO;

public class BookAssembler {
    public static BookDTO toDTO(Book book) {
        BookDTO dto = new BookDTO();
        dto.setId(book.getId());
        dto.setTitle(book.getTitle());
        dto.setDescription(book.getDescription());
        dto.setClassification(book.getClassification());
        if (book.getAuthor() != null) {
            dto.setAuthorId(book.getAuthor().getId());
        }
        dto.setEnable(book.getEnable());
        dto.setPrice(book.getPrice());
        dto.setStock(book.getStock());

        return dto;
    }

    public static Book toDO(BookDTO dto) {
        Book book = new Book();
        book.setId(dto.getId());
        book.setTitle(dto.getTitle());
        book.setDescription(dto.getDescription());
        book.setClassification(dto.getClassification());
        if (dto.getAuthorId() != null) {
            Author author = new Author();
            author.setId(dto.getAuthorId());
            book.setAuthor(author);
        }
        book.setEnable(dto.getEnable());
        book.setPrice(dto.getPrice());
        book.setStock(dto.getStock());

        return book;
    }
}
