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

    /**
     * Test successful request handling
     */
    @Test
    void testBFHLEndpointSuccess() throws Exception {
        BFHLRequest request = new BFHLRequest();
        request.setData(Arrays.asList("a", "1", "334", "4", "R", "$"));

        mockMvc.perform(post("/BFHL")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.is_success").value(true))
                .andExpect(jsonPath("$.user_id").value("tej_kaur_02011996"))
                .andExpect(jsonPath("$.email").value("tej@chitkara.com"))
                .andExpect(jsonPath("$.sum").value("339"));
    }

    /**
     * Test with empty array
     */
    @Test
    void testBFHLEndpointEmptyArray() throws Exception {
        BFHLRequest request = new BFHLRequest();
        request.setData(Arrays.asList());

        mockMvc.perform(post("/BFHL")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.is_success").value(false));
    }

    /**
     * Test with null data
     */
    @Test
    void testBFHLEndpointNullData() throws Exception {
        String jsonPayload = "{\"data\": null}";

        mockMvc.perform(post("/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonPayload))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.is_success").value(false));
    }

    /**
     * Test response field presence
     */
    @Test
    void testResponseFieldPresence() throws Exception {
        Request request = new Request();
        request.setData(Arrays.asList("1", "a", "$"));

        mockMvc.perform(post("/")
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

    /**
     * Test API endpoint path
     */
    @Test
    void testApiEndpointPath() throws Exception {
        Request request = new Request();
        request.setData(Arrays.asList("test"));

        mockMvc.perform(post("/BFHL")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }
}