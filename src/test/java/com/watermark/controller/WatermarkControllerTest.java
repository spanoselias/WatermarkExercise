package com.watermark.controller;

import com.watermark.repository.ProcessingRepository;
import com.watermark.service.WatermarkService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class WatermarkControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WatermarkService watermarkService;

    @MockBean
    private ProcessingRepository processingRepository;

    @Test
    void createWatermark() throws Exception {

        final String bodyRequest = """
                {
                    "content": "book",
                    "title": "The Dark Code",
                    "author": "Bruce Wayne",
                    "topic": "Bruce Wayne"
                }""";

        mockMvc.perform(post("/watermark")
                        .contentType(APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(bodyRequest))
                .andExpect(status().isOk());
    }

    @Test
    void given_valid_ticket_id() throws Exception {

        mockMvc.perform(get("/watermark/{ticketId}", "mockTicketId")
                        .contentType(APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}