package com.crowdstreet.assignment.service;

import com.crowdstreet.assignment.client.ExampleClient;
import com.crowdstreet.assignment.data.DocumentRequestRepository;
import com.crowdstreet.assignment.data.model.CallbackRequest;
import com.crowdstreet.assignment.data.model.DocumentRequest;
import com.crowdstreet.assignment.data.model.DocumentRequestRequestBody;
import com.crowdstreet.assignment.data.model.ProcessingStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DocumentRequestService {
    private final DocumentRequestRepository documentRequestRepository;
    private final ExampleClient exampleClient;

    @Autowired
    public DocumentRequestService(DocumentRequestRepository documentRequestRepository,
                                  ExampleClient exampleClient) {
        this.documentRequestRepository = documentRequestRepository;
        this.exampleClient = exampleClient;
    }

    @Transactional
    public void createDocumentRequest(DocumentRequestRequestBody requestBody) {
        DocumentRequest documentRequest = documentRequestRepository
                .save(new DocumentRequest(requestBody));
        exampleClient.performCallbackRequest(CallbackRequest.from(documentRequest));
    }

    public void updateStatus(Long id, ProcessingStatus status) {
        documentRequestRepository.updateStatus(id, status);
    }
}
