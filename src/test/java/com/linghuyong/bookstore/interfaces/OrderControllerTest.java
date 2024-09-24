package com.linghuyong.bookstore.interfaces;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.linghuyong.bookstore.infrastructure.common.api.Response;
import com.linghuyong.bookstore.interfaces.dto.BookDTO;
import com.linghuyong.bookstore.interfaces.dto.OrderDTO;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.charset.StandardCharsets;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerTest {

    @Resource
    private MockMvc mockMvc;

    private final Gson gson = new Gson();

    // 创建订单, 取消订单的流程测试
    @Test
    @WithMockUser(username="1", authorities = {"user", "admin"})
    public void createOrder() throws Exception {
        // 将book1 设置库存为10
        BookDTO dto = new BookDTO();
        dto.setStock(10);
        MockHttpServletResponse response = mockMvc.perform(
                        MockMvcRequestBuilders.put("/admin/book/1")
                                .with(csrf())
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content(gson.toJson(dto))
                                .characterEncoding(StandardCharsets.UTF_8)
                )
                .andReturn().getResponse();

        // 创建book1的订单
        response = mockMvc.perform(
                        MockMvcRequestBuilders.post("/api/order")
                                .with(csrf())
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content("1")
                                .characterEncoding(StandardCharsets.UTF_8)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse();
        System.out.println(response.getContentAsString());
        TypeToken<Response<OrderDTO>> responseTypeToken = new TypeToken<>() {};
        Response<OrderDTO> orderDTO = gson.fromJson(response.getContentAsString(), responseTypeToken);

        // 检查book1的库存为9
        response = mockMvc.perform(
                        MockMvcRequestBuilders.get("/admin/book/1")
                                .with(csrf())
                                .characterEncoding(StandardCharsets.UTF_8)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.data.stock").value(dto.getStock() - 1))
                .andReturn().getResponse();

        // 取消book1的订单
        response = mockMvc.perform(
                        MockMvcRequestBuilders.post("/api/order/"+orderDTO.getData().getId()+"/cancel")
                                .with(csrf())
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .characterEncoding(StandardCharsets.UTF_8)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse();
        System.out.println(response.getContentAsString());

        // 检查book1的库存为10
        response = mockMvc.perform(
                        MockMvcRequestBuilders.get("/admin/book/1")
                                .with(csrf())
                                .characterEncoding(StandardCharsets.UTF_8)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.data.stock").value(dto.getStock()))
                .andReturn().getResponse();
    }
}
