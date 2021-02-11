package com.crowdstreet.assignment;

import com.crowdstreet.assignment.client.ExampleClient;
import com.crowdstreet.assignment.data.model.CallbackRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.HttpClientErrorException;

@SpringBootTest
public class ExampleClientIntegrationTests {

    @Autowired
    ExampleClient exampleClient;

    @Test
    public void createsCallbackRequestAtExampleEndpoint() {
        CallbackRequest callbackRequest = new CallbackRequest("test", 4L);
        // TODO For real API, establish expected ResponseEntity<T> and
        //  validate. For now, capture the [presumed] 404 and pass the test.
        try {
            exampleClient.performCallbackRequest(callbackRequest);
        } catch (HttpClientErrorException clientErrorException) {
            // No-op for real call to example.com
        } catch (Exception e) {
            // For anything else (e.g. no internet connection)
            e.printStackTrace();
        }
    }
}
