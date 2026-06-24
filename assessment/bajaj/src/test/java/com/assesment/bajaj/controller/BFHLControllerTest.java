package com.assesment.bajaj.controller;

import com.assesment.bajaj.dto.BFHLRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class BFHLControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testBFHLEndpointSuccess() throws Exception {

        BFHLRequest request = new BFHLRequest();
        request.setData(Arrays.asList("a", "1", "334", "4", "R", "$"));

        mockMvc.perform(post("/BFHL")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.is_success").value(true))
                .andExpect(jsonPath("$.sum").value("339"))
                .andExpect(jsonPath("$.odd_numbers[0]").value("1"))
                .andExpect(jsonPath("$.even_numbers[0]").value("334"))
                .andExpect(jsonPath("$.alphabets[0]").value("A"));
    }

    @Test
    void testBFHLEndpointExampleB() throws Exception {

        BFHLRequest request = new BFHLRequest();
        request.setData(Arrays.asList(
                "2", "a", "y", "4",
                "&", "-", "*",
                "5", "92", "b"
        ));

        mockMvc.perform(post("/BFHL")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.is_success").value(true))
                .andExpect(jsonPath("$.sum").value("103"))
                .andExpect(jsonPath("$.concat_string").value("ByA"));
    }

    @Test
    void testResponseFieldsExist() throws Exception {

        BFHLRequest request = new BFHLRequest();
        request.setData(Arrays.asList("1", "a", "$"));

        mockMvc.perform(post("/BFHL")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.is_success").exists())
                .andExpect(jsonPath("$.user_id").exists())
                .andExpect(jsonPath("$.email").exists())
                .andExpect(jsonPath("$.roll_number").exists())
                .andExpect(jsonPath("$.odd_numbers").exists())
                .andExpect(jsonPath("$.even_numbers").exists())
                .andExpect(jsonPath("$.alphabets").exists())
                .andExpect(jsonPath("$.special_characters").exists())
                .andExpect(jsonPath("$.sum").exists())
                .andExpect(jsonPath("$.concat_string").exists());
    }
}