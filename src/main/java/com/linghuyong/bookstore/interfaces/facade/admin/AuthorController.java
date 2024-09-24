package com.linghuyong.bookstore.interfaces.facade.admin;

import com.linghuyong.bookstore.application.service.BookApplicationService;
import com.linghuyong.bookstore.domain.book.entity.Author;
import com.linghuyong.bookstore.infrastructure.common.api.Response;
import com.linghuyong.bookstore.interfaces.assembler.AuthorAssembler;
import com.linghuyong.bookstore.interfaces.dto.AuthorDTO;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/*
    admin后台接口，使用basic的认证方式
    作者管理接口
 */
@RestController
@RequestMapping("/admin/author")
public class AuthorController {
    @Resource
    private BookApplicationService bookApplicationService;

    @PostMapping("")
    public Response<AuthorDTO> addAuthor(@RequestBody AuthorDTO authorDTO) {
        Author author = AuthorAssembler.toDO(authorDTO);
        author = bookApplicationService.addAuthor(author);

        return Response.ok(AuthorAssembler.toDTO(author));
    }

    @PutMapping("/{authorId}")
    public Response<AuthorDTO> updateAuthor(@PathVariable long authorId, @RequestBody AuthorDTO authorDTO) {
        Author author = AuthorAssembler.toDO(authorDTO);
        author.setId(authorId);
        author = bookApplicationService.updateAuthor(author);

        return Response.ok(AuthorAssembler.toDTO(author));
    }
}
