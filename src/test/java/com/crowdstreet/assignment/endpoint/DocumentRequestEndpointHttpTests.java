package com.crowdstreet.assignment.endpoint;

import com.crowdstreet.assignment.client.ExampleClient;
import com.crowdstreet.assignment.data.model.DocumentRequestRequestBody;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class DocumentRequestEndpointHttpTests {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MockMvc mvc;

    @Autowired
    private RestTemplate restTemplate;

    private MockRestServiceServer mockServer;

    @BeforeEach
    public void init() {
        mockServer = MockRestServiceServer.createServer(restTemplate);
    }

    @Test
    public void rejectsInvalidBodyAsBadRequest() throws Exception {
        mvc.perform(post("/request")
                .contentType(MediaType.APPLICATION_JSON)
                .content("invalid"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void acceptsValidBody() throws Exception {
        mockServer.expect(ExpectedCount.once(),
                requestTo(String.format("%s/request", ExampleClient.API_BASE)))
                .andRespond(withStatus(HttpStatus.OK));
        mvc.perform(post("/request")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new DocumentRequestRequestBody("test"))))
                .andExpect(status().isOk());
    }

}