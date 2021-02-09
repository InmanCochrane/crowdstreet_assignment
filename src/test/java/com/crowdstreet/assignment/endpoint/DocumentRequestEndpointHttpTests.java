package com.crowdstreet.assignment.endpoint;

import com.crowdstreet.assignment.data.model.DocumentRequestRequestBody;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class DocumentRequestEndpointHttpTests {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MockMvc mvc;

    @Test
    public void rejectsInvalidBodyAsBadRequest() throws Exception {
        mvc.perform(post("/request")
                .contentType(MediaType.APPLICATION_JSON)
                .content("invalid"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void acceptsValidBody() throws Exception {
        mvc.perform(post("/request")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new DocumentRequestRequestBody("test"))))
                .andExpect(status().isOk());
    }

}