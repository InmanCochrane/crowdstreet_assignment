package com.crowdstreet.assignment.endpoint;

import com.crowdstreet.assignment.data.DocumentRequestRepository;
import com.crowdstreet.assignment.data.model.DocumentRequest;
import com.crowdstreet.assignment.data.model.DocumentRequestRequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DocumentRequestEndpoint {
    private final DocumentRequestRepository documentRequestRepository;

    @Autowired
    public DocumentRequestEndpoint(DocumentRequestRepository documentRequestRepository) {
        this.documentRequestRepository = documentRequestRepository;
    }

    @PostMapping("/request")
    public void createDocumentRequest(@RequestBody DocumentRequestRequestBody requestBody) {
        documentRequestRepository.save(new DocumentRequest(requestBody));
    }
}
