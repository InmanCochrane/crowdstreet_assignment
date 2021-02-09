package com.crowdstreet.assignment.endpoint;

import com.crowdstreet.assignment.client.ExampleClient;
import com.crowdstreet.assignment.data.DocumentRequestRepository;
import com.crowdstreet.assignment.data.model.CallbackRequest;
import com.crowdstreet.assignment.data.model.DocumentRequest;
import com.crowdstreet.assignment.data.model.DocumentRequestRequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DocumentRequestEndpoint {
    private final DocumentRequestRepository documentRequestRepository;
    private final ExampleClient exampleClient;

    @Autowired
    public DocumentRequestEndpoint(DocumentRequestRepository documentRequestRepository, ExampleClient exampleClient) {
        this.documentRequestRepository = documentRequestRepository;
        this.exampleClient = exampleClient;
    }

    @Transactional
    @PostMapping("/request")
    public void createDocumentRequest(@RequestBody DocumentRequestRequestBody requestBody) {
        DocumentRequest documentRequest = documentRequestRepository
                .save(new DocumentRequest(requestBody));
        exampleClient.performCallbackRequest(CallbackRequest.from(documentRequest));
    }
}
