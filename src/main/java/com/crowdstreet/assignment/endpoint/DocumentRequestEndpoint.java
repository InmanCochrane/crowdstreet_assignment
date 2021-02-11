package com.crowdstreet.assignment.endpoint;

import com.crowdstreet.assignment.data.model.DocumentRequestRequestBody;
import com.crowdstreet.assignment.service.DocumentRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

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

    @PostMapping(value = "/callback/{id}", consumes = "text/plain")
    public ResponseEntity<Object> createDocumentRequestProcessingStatus(
            @PathVariable Long id,
            @RequestBody String startedStatus) {
        if (!Objects.equals(startedStatus, "STARTED")) {
            return ResponseEntity.badRequest().build();
        }
        documentRequestService.updateStatus(id, "STARTED");
        return ResponseEntity.noContent().build();
    }
}
