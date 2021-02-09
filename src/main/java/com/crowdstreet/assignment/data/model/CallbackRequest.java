package com.crowdstreet.assignment.data.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class CallbackRequest {
    @JsonProperty
    public String body;

    @JsonProperty
    public String callbackAddress;

    public CallbackRequest(String body, Long id) {
        this.body = body;
        this.callbackAddress = String.format("/callback/%d", id);
    }

    public static CallbackRequest from(DocumentRequest documentRequest) {
        return new CallbackRequest(documentRequest.body, documentRequest.id);
    }

    @Override
    public boolean equals(Object o) {
        try {
            CallbackRequest callbackRequest = (CallbackRequest) o;
            return Objects.equals(this.body, callbackRequest.body) &&
                    Objects.equals(this.callbackAddress, callbackRequest.callbackAddress);
        } catch (Exception e) {
            return false;
        }
    }
}
