package com.crowdstreet.assignment.endpoint;

import com.crowdstreet.assignment.data.model.DocumentRequestRequestBody;
import com.crowdstreet.assignment.service.DocumentRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DocumentRequestEndpoint {
    private final DocumentRequestService documentRequestService;

    @Autowired
    public DocumentRequestEndpoint(DocumentRequestService documentRequestService) {
        this.documentRequestService = documentRequestService;
    }

    @PostMapping("/request")
    public void createDocumentRequest(@RequestBody DocumentRequestRequestBody requestBody) {
        documentRequestService.createDocumentRequest(requestBody);
    }
}
