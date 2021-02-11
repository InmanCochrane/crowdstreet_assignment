package com.crowdstreet.assignment.endpoint;

import com.crowdstreet.assignment.client.ExampleClient;
import com.crowdstreet.assignment.data.DocumentRequestRepository;
import com.crowdstreet.assignment.data.model.*;
import com.crowdstreet.assignment.service.DocumentRequestService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DocumentRequestEndpointUnitTests {

    @Mock
    DocumentRequestRepository mockDocumentRequestRepository;

    @Mock
    RestTemplate mockRestTemplate;

    DocumentRequestEndpoint memoryBoundedEndpoint;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        memoryBoundedEndpoint = new DocumentRequestEndpoint(
                new DocumentRequestService(
                        mockDocumentRequestRepository,
                        new ExampleClient(mockRestTemplate)
                ));
    }

    @Test
    public void createsDocumentRequestFromRequestBody() {
        String testBodyValue = "test";
        DocumentRequestRequestBody documentRequestRequestBody =
                new DocumentRequestRequestBody(testBodyValue);
        DocumentRequest documentRequest = new DocumentRequest(documentRequestRequestBody);
        when(mockDocumentRequestRepository
                .save(any(DocumentRequest.class)))
                .thenReturn(documentRequest);

        ArgumentCaptor<DocumentRequest> documentRequestArgument =
                ArgumentCaptor.forClass(DocumentRequest.class);
        memoryBoundedEndpoint.createDocumentRequest(documentRequestRequestBody);
        verify(mockDocumentRequestRepository).save(documentRequestArgument.capture());
        assertEquals(testBodyValue, documentRequestArgument.getValue().body);
    }

    @Test
    public void performsCallbackRequestOnDocumentRequestCreation() {
        DocumentRequestRequestBody documentRequestRequestBody =
                new DocumentRequestRequestBody("test");
        DocumentRequest documentRequest = new DocumentRequest(documentRequestRequestBody);
        when(mockDocumentRequestRepository
                .save(any(DocumentRequest.class)))
                .thenReturn(documentRequest);

        memoryBoundedEndpoint.createDocumentRequest(documentRequestRequestBody);
        verify(mockRestTemplate).postForEntity(
                ArgumentMatchers.eq("https://www.example.com/request"),
                ArgumentMatchers.eq(CallbackRequest.from(documentRequest)),
                any()
        );
    }

    @Test
    public void updatesDocumentRequestProcessingStatusOnCallbackPost() {
        long documentRequestId = ThreadLocalRandom.current().nextLong();
        memoryBoundedEndpoint.createDocumentRequestProcessingStatus(
                documentRequestId, ProcessingStatus.STARTED.toString());
        verify(mockDocumentRequestRepository)
                .updateStatus(documentRequestId, ProcessingStatus.STARTED);
    }

    @Test
    public void updatesDocumentRequestProcessingStatusOnCallbackPut() {
        Arrays.stream(ProcessingStatus.values()).forEach(status -> {
            long documentRequestId = ThreadLocalRandom.current().nextLong();
            DocumentProcessingStatusBody processingStatus =
                    new DocumentProcessingStatusBody(
                            status,
                            "test " + documentRequestId
                    );
            memoryBoundedEndpoint.updateDocumentRequestProcessingStatus(
                    documentRequestId, processingStatus);
            verify(mockDocumentRequestRepository)
                    .updateStatus(documentRequestId, processingStatus.status);
        });
    }
}