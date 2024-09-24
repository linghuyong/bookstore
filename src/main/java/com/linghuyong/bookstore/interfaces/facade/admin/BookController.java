package com.linghuyong.bookstore.interfaces.facade.admin;

import com.linghuyong.bookstore.application.service.BookApplicationService;
import com.linghuyong.bookstore.domain.book.entity.Book;
import com.linghuyong.bookstore.infrastructure.common.api.Response;
import com.linghuyong.bookstore.interfaces.assembler.BookAssembler;
import com.linghuyong.bookstore.interfaces.dto.BookDTO;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/*
    admin后台接口，使用basic的认证方式
    图书管理接口
 */
@RestController
@RequestMapping("/admin/book")
public class BookController {
    @Resource
    private BookApplicationService bookApplicationService;

    @PostMapping("")
    public Response<BookDTO> addBook(@RequestBody BookDTO dto) {
        Book book = BookAssembler.toDO(dto);
        book = bookApplicationService.addBook(book);

        return Response.ok(BookAssembler.toDTO(book));
    }

    @PutMapping("/{bookId}")
    public Response<BookDTO> updateBook(@PathVariable long bookId, @RequestBody BookDTO dto) {
        Book book = BookAssembler.toDO(dto);
        book.setId(bookId);
        book = bookApplicationService.updateBook(book);

        return Response.ok(BookAssembler.toDTO(book));
    }

    @GetMapping("/{bookId}")
    public Response<BookDTO> getBook(@PathVariable long bookId) {
        Book book = bookApplicationService.getBook(bookId);

        return Response.ok(BookAssembler.toDTO(book));
    }
}
