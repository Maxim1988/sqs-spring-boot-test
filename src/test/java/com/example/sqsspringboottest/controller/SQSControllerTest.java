package com.example.sqsspringboottest.controller;

import com.example.sqsspringboottest.controllers.SQSController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = SQSController.class)
public class SQSControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private QueueMessagingTemplate queueMessagingTemplate;

    @Test
    public void sendMessageTest() throws Exception {
        mockMvc.perform(get("/sqs/send/{message}", "Hello message")
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }
}
