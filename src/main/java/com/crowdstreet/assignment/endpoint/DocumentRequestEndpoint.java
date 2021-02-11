package com.crowdstreet.assignment.endpoint;

import com.crowdstreet.assignment.data.model.DocumentProcessingStatusBody;
import com.crowdstreet.assignment.data.model.DocumentRequest;
import com.crowdstreet.assignment.data.model.DocumentRequestRequestBody;
import com.crowdstreet.assignment.data.model.ProcessingStatus;
import com.crowdstreet.assignment.service.DocumentRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
public class DocumentRequestEndpoint {
    private final DocumentRequestService documentRequestService;

    @Autowired
    public DocumentRequestEndpoint(DocumentRequestService documentRequestService) {
        this.documentRequestService = documentRequestService;
    }

    @GetMapping(value = "/status/{id}")
    public ResponseEntity<DocumentRequest> getDocumentRequest(@PathVariable Long id) {
        return documentRequestService.get(id).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/request")
    public void createDocumentRequest(@RequestBody DocumentRequestRequestBody requestBody) {
        documentRequestService.createDocumentRequest(requestBody);
    }

    @PostMapping(value = "/callback/{id}", consumes = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<Object> createDocumentRequestProcessingStatus(
            @PathVariable Long id,
            @RequestBody String startedStatus) {
        if (!Objects.equals(startedStatus, ProcessingStatus.STARTED.toString())) {
            return ResponseEntity.badRequest().build();
        }
        documentRequestService.updateStatus(id, ProcessingStatus.STARTED);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/callback/{id}")
    public ResponseEntity<Object> updateDocumentRequestProcessingStatus(
            @PathVariable Long id,
            @RequestBody DocumentProcessingStatusBody processingStatus) {
        documentRequestService.updateStatus(id, processingStatus.status, processingStatus.detail);
        return ResponseEntity.noContent().build();
    }
}
