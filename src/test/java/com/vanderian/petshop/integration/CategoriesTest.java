package com.vanderian.petshop.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import sk.vanderian.petshop.App;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// we should use a better framework to set up also data eg. testcontainers.org
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = App.class)
@AutoConfigureMockMvc
public class CategoriesTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

//    we could mock the response to better check the test value
//    @MockBean
//    private CategoryService categoryService;

    @Test
    @WithMockUser(username = "user", password = "pass", roles = "ADMIN")
    void adminHasAccessToCategories() throws Exception {
        mockMvc.perform(get("/categories"))
                .andExpect(content().contentType("application/json"))
                .andExpect(content().string("[{\"id\":1,\"name\":\"dogs\"},{\"id\":2,\"name\":\"cats\"},{\"id\":3,\"name\":\"other\"}]"))
                .andExpect(status().isOk());
    }
}
