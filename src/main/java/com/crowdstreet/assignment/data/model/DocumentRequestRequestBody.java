package com.crowdstreet.assignment.data.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class DocumentRequestRequestBody implements Serializable {

    @JsonProperty
    public String body;

    @JsonCreator
    public DocumentRequestRequestBody(@JsonProperty String body) {
        this.body = body;
    }
}
