package com.linghuyong.bookstore.interfaces.facade;

import com.linghuyong.bookstore.application.service.PageApplicationService;
import com.linghuyong.bookstore.domain.book.entity.Book;
import com.linghuyong.bookstore.domain.security.service.AuthenticatedUserService;
import com.linghuyong.bookstore.infrastructure.common.api.Response;
import com.linghuyong.bookstore.interfaces.assembler.BookAssembler;
import com.linghuyong.bookstore.interfaces.dto.BookDTO;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/page")
public class PageController {

    @Resource
    private PageApplicationService pageApplicationService;

    @Resource
    private AuthenticatedUserService authenticatedUserService;

    // 获取推荐图书
    @GetMapping("/recommend")
    public Response<List<BookDTO>> getRecommend() throws Exception {
        List<Book> books = pageApplicationService.getRecommend(authenticatedUserService.getAuthenticatedUserId());
        return Response.ok(books.stream().map(BookAssembler::toDTO).collect(Collectors.toList()));
    }
}
