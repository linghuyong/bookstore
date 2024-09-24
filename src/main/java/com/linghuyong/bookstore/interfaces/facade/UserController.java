package com.linghuyong.bookstore.interfaces.facade;

import com.linghuyong.bookstore.application.service.UserApplicationService;
import com.linghuyong.bookstore.domain.security.entity.JWTToken;
import com.linghuyong.bookstore.domain.security.service.AuthenticatedUserService;
import com.linghuyong.bookstore.domain.user.entity.User;
import com.linghuyong.bookstore.infrastructure.common.api.Response;
import com.linghuyong.bookstore.interfaces.assembler.LoginTokenAssembler;
import com.linghuyong.bookstore.interfaces.assembler.UserAssembler;
import com.linghuyong.bookstore.interfaces.dto.ErrorCode;
import com.linghuyong.bookstore.interfaces.dto.LoginTokenDTO;
import com.linghuyong.bookstore.interfaces.dto.UserDTO;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/*
    C端接口，使用JWTToken鉴权
    User相关接口
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Resource
    private UserApplicationService userApplicationService;

    @Resource
    private AuthenticatedUserService authenticatedUserService;

    // 用户注册接口
    @PostMapping("/register")
    public Response<UserDTO> registerUser(@RequestBody UserDTO userDTO) throws Exception {
        User user = UserAssembler.toDO(userDTO);
        user = userApplicationService.registerUser(user, userDTO.getPassword());

        return Response.ok(UserAssembler.toDTO(user));
    }

    // 用户登录接口
    @GetMapping("/login")
    public Response<LoginTokenDTO> login(@RequestParam String username, @RequestParam String password) throws Exception {
        JWTToken token = userApplicationService.login(username, password);
        if (token == null) {
            return Response.failed(ErrorCode.AuthFailed,"login failed.");
        }

        return Response.ok(LoginTokenAssembler.toDTO(token));
    }

    // 登录的用户，获取自己的信息
    @GetMapping("/self")
    public Response<UserDTO> getUserInfo() {
        Long userId = authenticatedUserService.getAuthenticatedUserId();
        return Response.ok(UserAssembler.toDTO(userApplicationService.getUser(userId)));
    }
}
