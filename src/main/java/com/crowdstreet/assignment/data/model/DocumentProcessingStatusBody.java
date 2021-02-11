package com.crowdstreet.assignment.data.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DocumentProcessingStatusBody {
    @JsonProperty
    public ProcessingStatus status;

    @JsonProperty
    public String detail;

    @JsonCreator
    public DocumentProcessingStatusBody(@JsonProperty ProcessingStatus status,
                                        @JsonProperty String detail) {
        this.status = status;
        this.detail = detail;
    }
}
