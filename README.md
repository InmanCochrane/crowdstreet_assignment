# Run

This service is built on Spring Boot and Maven. To run it,
execute `./mvnw spring-boot:run` in the root directory of the project.

# API

This service contains a simple implementation of the assigned API. To see it in
action and touch on every existing endpoint, perform the following steps.

1. Start the service, ensuring nothing else is using a conflicting port
   (8080 is the default).
1. Try to GET a non-existent DocumentRequest: `curl -i localhost:8080/status/1`.
   The `-i` flag will ensure you see the expected
   `404` response for the requested ID (`1`).
1. Create a
   DocumentRequest: `curl -i -X POST localhost:8080/request -H "Content-Type: application/json" -d '{"body": "test"}'`
  1. The `Content-Type` header and a valid JSON body structured as this example
     shows are required.
1. [Optional] Validate the DocumentRequest has been
   created: `curl -i localhost:8080/status/1`.
1. From the perspective of the third-party service that will have been provided
   the document for processing and executed a call to the provided callback URL,
   POST the news that processing has
   started: `curl -i -X POST localhost:8080/callback/1 -H "Content-Type: text/plain" -d "STARTED"`
   .
  1. The `Content-Type` header and a valid string body with precisely the value
     provided in this example are required.
1. [Optional] Validate the DocumentRequest has been
   updated: `curl -i localhost:8080/status/1`.
1. From the perspective of the third-party service, execute an update to the
   document's processing
   status: `curl -i -X PUT localhost:8080/callback/1 -H "Content-Type: application/json" -d '{"status":"COMPLETED","detail":"😄"}'`
   .
  1. In addition to `COMPLETED`, the other valid values for `status` are
     `PROCESSING` (which perhaps this imaginary third-party service would
     generally send first) and `ERROR`.
  2. The `Content-Type` header and a valid JSON body structured as this example
     shows are required.
1. [Optional] Validate the DocumentRequest has been
   updated: `curl -i localhost:8080/status/1`.
