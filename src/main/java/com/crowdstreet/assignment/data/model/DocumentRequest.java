package com.crowdstreet.assignment.data.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class DocumentRequest {
    public String body;

    public String status;

    @Id
    @GeneratedValue
    public Long id;

    public DocumentRequest(DocumentRequestRequestBody requestBody) {
        this.body = requestBody.body;
    }

    public DocumentRequest() {}
}
