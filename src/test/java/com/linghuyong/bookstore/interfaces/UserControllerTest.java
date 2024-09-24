package com.linghuyong.bookstore.interfaces;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.linghuyong.bookstore.infrastructure.common.api.Response;
import com.linghuyong.bookstore.interfaces.dto.LoginTokenDTO;
import com.linghuyong.bookstore.interfaces.dto.UserDTO;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.charset.StandardCharsets;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Resource
    private MockMvc mockMvc;


    // 注册接口测试
    @Test
    public void register() throws Exception {
        Gson gson = new Gson();
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("test");
        userDTO.setEmail("test@hotmail.com");
        userDTO.setPassword("test@123456");

        MockHttpServletResponse response = mockMvc.perform(
                        MockMvcRequestBuilders.post("/api/user/register")
                                .with(csrf())
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content(gson.toJson(userDTO))
                                .characterEncoding(StandardCharsets.UTF_8)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse();
        System.out.println(response.getContentAsString());
    }

    // 登录接口测试
    @Test
    public void login() throws Exception {
        String username = "test";
        String password = "test@123456";

        MockHttpServletResponse response = mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/user/login")
                                .with(csrf())
                                .param("username", username)
                                .param("password", password)
                                .characterEncoding(StandardCharsets.UTF_8)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse();
        System.out.println(response.getContentAsString());

        // 调用self接口获取用户信息测试
        Gson gson = new Gson();
        TypeToken<Response<LoginTokenDTO>> responseTypeToken = new TypeToken<>() {};
        Response<LoginTokenDTO> tokenDTO = gson.fromJson(response.getContentAsString(), responseTypeToken);

        response = mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/user/self")
                                .with(csrf())
                                .header("token", tokenDTO.getData().getToken())
                                .characterEncoding(StandardCharsets.UTF_8)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.data.username").value(username))
                .andReturn().getResponse();
        System.out.println(response.getContentAsString());
    }
}
