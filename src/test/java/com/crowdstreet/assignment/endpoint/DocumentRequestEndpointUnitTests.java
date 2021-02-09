package com.crowdstreet.assignment.endpoint;

import com.crowdstreet.assignment.data.DocumentRequestRepository;
import com.crowdstreet.assignment.data.model.DocumentRequest;
import com.crowdstreet.assignment.data.model.DocumentRequestRequestBody;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

public class DocumentRequestEndpointUnitTests {

    @Mock
    DocumentRequestRepository documentRequestRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void createsDocumentRequestFromRequestBody() {
        DocumentRequestEndpoint endpoint = new DocumentRequestEndpoint(documentRequestRepository);
        ArgumentCaptor<DocumentRequest> argument = ArgumentCaptor.forClass(DocumentRequest.class);
        String testBodyValue = "test";
        endpoint.createDocumentRequest(new DocumentRequestRequestBody(testBodyValue));
        verify(documentRequestRepository).save(argument.capture());
        assertEquals(testBodyValue, argument.getValue().body);
    }
}