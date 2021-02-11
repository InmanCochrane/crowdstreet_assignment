package com.crowdstreet.assignment.data.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DocumentRequestTest {
    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void serializesToRequiredJsonSchema() throws JsonProcessingException {
        DocumentRequest documentRequest = new DocumentRequest();
        documentRequest.id = 490L;
        documentRequest.body = "test body";
        documentRequest.status = ProcessingStatus.ERROR;
        documentRequest.detail = "test detail";

        String expected = "{" +
                "\"body\":" +
                "\"" + documentRequest.body + "\"," +
                "\"status\":" +
                "\"" + documentRequest.status + "\"," +
                "\"detail\":" +
                "\"" + documentRequest.detail + "\"," +
                "\"id\":" +
                documentRequest.id +
                "}";
        assertEquals(expected,
                objectMapper.writeValueAsString(documentRequest));
    }
}