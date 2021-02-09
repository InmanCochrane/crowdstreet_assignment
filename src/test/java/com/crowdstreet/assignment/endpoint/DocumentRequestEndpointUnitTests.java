package com.crowdstreet.assignment.endpoint;

import com.crowdstreet.assignment.client.ExampleClient;
import com.crowdstreet.assignment.data.DocumentRequestRepository;
import com.crowdstreet.assignment.data.model.CallbackRequest;
import com.crowdstreet.assignment.data.model.DocumentRequest;
import com.crowdstreet.assignment.data.model.DocumentRequestRequestBody;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DocumentRequestEndpointUnitTests {

    @Mock
    DocumentRequestRepository mockDocumentRequestRepository;

    @Mock
    RestTemplate mockRestTemplate;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void createsDocumentRequestFromRequestBody() {
        ExampleClient exampleClient = new ExampleClient(mockRestTemplate);
        DocumentRequestEndpoint endpoint = new
                DocumentRequestEndpoint(mockDocumentRequestRepository, exampleClient);

        String testBodyValue = "test";
        DocumentRequestRequestBody documentRequestRequestBody =
                new DocumentRequestRequestBody(testBodyValue);
        DocumentRequest documentRequest = new DocumentRequest(documentRequestRequestBody);
        when(mockDocumentRequestRepository
                .save(any(DocumentRequest.class)))
                .thenReturn(documentRequest);

        ArgumentCaptor<DocumentRequest> argument = ArgumentCaptor.forClass(DocumentRequest.class);
        endpoint.createDocumentRequest(documentRequestRequestBody);
        verify(mockDocumentRequestRepository).save(argument.capture());
        assertEquals(testBodyValue, argument.getValue().body);
    }

    @Test
    public void performsCallbackRequestOnDocumentRequestCreation() {
        ExampleClient exampleClient = new ExampleClient(mockRestTemplate);
        DocumentRequestEndpoint endpoint = new
                DocumentRequestEndpoint(mockDocumentRequestRepository, exampleClient);

        DocumentRequestRequestBody documentRequestRequestBody =
                new DocumentRequestRequestBody("test");
        DocumentRequest documentRequest = new DocumentRequest(documentRequestRequestBody);
        when(mockDocumentRequestRepository
                .save(any(DocumentRequest.class)))
                .thenReturn(documentRequest);

        endpoint.createDocumentRequest(documentRequestRequestBody);
        verify(mockRestTemplate).postForEntity(
                ArgumentMatchers.eq("https://www.example.com/request"),
                ArgumentMatchers.eq(CallbackRequest.from(documentRequest)),
                any()
        );
    }

}