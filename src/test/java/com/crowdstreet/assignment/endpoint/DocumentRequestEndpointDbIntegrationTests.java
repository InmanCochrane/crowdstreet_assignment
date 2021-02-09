package com.crowdstreet.assignment.endpoint;

import com.crowdstreet.assignment.client.ExampleClient;
import com.crowdstreet.assignment.data.DocumentRequestRepository;
import com.crowdstreet.assignment.data.model.DocumentRequestRequestBody;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
public class DocumentRequestEndpointDbIntegrationTests {
    @Mock
    private RestTemplate mockRestTemplate;

    @Autowired
    private DocumentRequestRepository documentRequestRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Disabled("Unable to establish transaction manager in test context.")
    @Test
    public void doesNotPersistDocumentRequestWhenCallbackRequestFails() {
        ExampleClient exampleClient = new ExampleClient(mockRestTemplate);
        DocumentRequestEndpoint endpoint = new
                DocumentRequestEndpoint(documentRequestRepository,
                exampleClient);

        when(mockRestTemplate.postForEntity(anyString(), any(), any()))
                .thenThrow(new HttpClientErrorException(HttpStatus.BAD_REQUEST));

        try {
            endpoint.createDocumentRequest(new DocumentRequestRequestBody("test"));
            fail("Expected exception did not occur");
        } catch (Exception e) {
            assertEquals(0, documentRequestRepository.count());
        }
    }
}
