package com.crowdstreet.assignment.data;

import com.crowdstreet.assignment.data.model.DocumentRequest;
import com.crowdstreet.assignment.data.model.DocumentRequestRequestBody;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;


@DataJpaTest
class DocumentRequestRepositoryTest {

    @Autowired
    DocumentRequestRepository documentRequestRepository;

    @Test
    public void createsDocumentRequest() {
        DocumentRequest documentRequest =
                new DocumentRequest(new DocumentRequestRequestBody("test"));
        DocumentRequest savedDocumentRequest = documentRequestRepository.save(documentRequest);
        assertEquals(documentRequest,
                documentRequestRepository.findById(savedDocumentRequest.id).orElseThrow());
    }

    @Test
    public void updatesDocumentRequestStatus() {
        DocumentRequest documentRequest =
                new DocumentRequest(new DocumentRequestRequestBody("test"));
        DocumentRequest savedDocumentRequest = documentRequestRepository.save(documentRequest);
        String status = "TEST STATUS";
        documentRequestRepository.updateStatus(savedDocumentRequest.id, status);
        assertEquals(status, documentRequestRepository.findById(savedDocumentRequest.id).orElseThrow().status);
    }
}