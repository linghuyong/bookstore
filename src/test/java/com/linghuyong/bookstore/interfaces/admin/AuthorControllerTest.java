package com.linghuyong.bookstore.interfaces.admin;

import com.google.gson.Gson;
import com.linghuyong.bookstore.interfaces.dto.AuthorDTO;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.charset.StandardCharsets;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthorControllerTest {
    @Resource
    private MockMvc mockMvc;


    // 新加作者测试
    @Test
    @WithMockUser(authorities = {"admin"})
    public void addAuthor() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(
                        MockMvcRequestBuilders.post("/admin/author")
                                .with(csrf())
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content("{\"name\": \"test\"}")
                                .characterEncoding(StandardCharsets.UTF_8)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse();
        System.out.println(response.getContentAsString());
    }

    // 修改作者测试
    @Test
    @WithMockUser(authorities = {"admin"})
    public void updateAuthor() throws Exception {
        Gson gson = new Gson();
        AuthorDTO dto = new AuthorDTO();
        dto.setName("updated");

        MockHttpServletResponse response = mockMvc.perform(
                        MockMvcRequestBuilders.put("/admin/author/1")
                                .with(csrf())
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content(gson.toJson(dto))
                                .characterEncoding(StandardCharsets.UTF_8)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.data.id").value(1))
                .andExpect(jsonPath("$.data.name").value(dto.getName()))
                .andReturn().getResponse();
        System.out.println(response.getContentAsString());
    }
}
