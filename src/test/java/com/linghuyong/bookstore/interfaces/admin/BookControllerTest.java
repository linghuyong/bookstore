package com.linghuyong.bookstore.interfaces.admin;

import com.google.gson.Gson;
import com.linghuyong.bookstore.interfaces.dto.BookDTO;
import com.linghuyong.bookstore.interfaces.dto.Classification;
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
public class BookControllerTest {
    @Resource
    private MockMvc mockMvc;

    private final Gson gson = new Gson();


    // 新加图书测试
    @Test
    @WithMockUser(authorities = {"admin"})
    public void addBook() throws Exception {
        BookDTO dto = new BookDTO();
        dto.setTitle("test book");
        dto.setDescription("this is a test book");
        dto.setClassification(Classification.ART.ordinal());
        dto.setAuthorId(1L);
        dto.setEnable(true);
        dto.setPrice(12.5);
        dto.setStock(100);

        MockHttpServletResponse response = mockMvc.perform(
                        MockMvcRequestBuilders.post("/admin/book")
                                .with(csrf())
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content(gson.toJson(dto))
                                .characterEncoding(StandardCharsets.UTF_8)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse();
        System.out.println(response.getContentAsString());
    }

    // 获取图书测试
    @Test
    @WithMockUser(authorities = {"admin"})
    public void getBook() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(
                        MockMvcRequestBuilders.get("/admin/book/1")
                                .with(csrf())
                                .characterEncoding(StandardCharsets.UTF_8)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.data.id").value(1))
                .andReturn().getResponse();
        System.out.println(response.getContentAsString());
    }

    // 获取图书失败测试
    @Test
    @WithMockUser(authorities = {"admin"})
    public void getBookFail() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(
                        MockMvcRequestBuilders.get("/admin/book/1000000")
                                .with(csrf())
                                .characterEncoding(StandardCharsets.UTF_8)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.status").value("FAILED"))
                .andReturn().getResponse();
        System.out.println(response.getContentAsString());
    }

    // 修改图书测试
    @Test
    @WithMockUser(authorities = {"admin"})
    public void updateBook() throws Exception {
        BookDTO dto = new BookDTO();

        // 修改标题
        dto.setTitle("changed title");
        MockHttpServletResponse response = mockMvc.perform(
                        MockMvcRequestBuilders.put("/admin/book/1")
                                .with(csrf())
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content(gson.toJson(dto))
                                .characterEncoding(StandardCharsets.UTF_8)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.data.id").value(1))
                .andExpect(jsonPath("$.data.title").value(dto.getTitle()))
                .andReturn().getResponse();
        System.out.println(response.getContentAsString());

        // 修改作者
        dto = new BookDTO();
        dto.setAuthorId(2L);
        response = mockMvc.perform(
                        MockMvcRequestBuilders.put("/admin/book/1")
                                .with(csrf())
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content(gson.toJson(dto))
                                .characterEncoding(StandardCharsets.UTF_8)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.data.id").value(1))
                .andExpect(jsonPath("$.data.authorId").value(dto.getAuthorId()))
                .andReturn().getResponse();
        System.out.println(response.getContentAsString());

        // 修改价格和库存
        dto = new BookDTO();
        dto.setPrice(20.5);
        dto.setStock(200);
        response = mockMvc.perform(
                        MockMvcRequestBuilders.put("/admin/book/1")
                                .with(csrf())
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content(gson.toJson(dto))
                                .characterEncoding(StandardCharsets.UTF_8)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.data.id").value(1))
                .andExpect(jsonPath("$.data.price").value(dto.getPrice()))
                .andExpect(jsonPath("$.data.stock").value(dto.getStock()))
                .andReturn().getResponse();
        System.out.println(response.getContentAsString());

        // 修改不存在对图书
        dto = new BookDTO();
        dto.setTitle("not exists");
        response = mockMvc.perform(
                        MockMvcRequestBuilders.put("/admin/book/1000000")
                                .with(csrf())
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content(gson.toJson(dto))
                                .characterEncoding(StandardCharsets.UTF_8)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse();
        System.out.println(response.getContentAsString());
    }

    // 修改图书失败测试
    @Test
    @WithMockUser(authorities = {"admin"})
    public void updateBookFail() throws Exception {
        BookDTO dto = new BookDTO();
        dto.setTitle("not exists");
        MockHttpServletResponse response = mockMvc.perform(
                        MockMvcRequestBuilders.put("/admin/book/1000000")
                                .with(csrf())
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content(gson.toJson(dto))
                                .characterEncoding(StandardCharsets.UTF_8)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.status").value("FAILED"))
                .andReturn().getResponse();
        System.out.println(response.getContentAsString());
    }
}
