# To The Evaluator

## Approach

I use TDD, which people who see my assignments often find it valuable to know.
As an effect of this, I suggest leaning on the tests to clarify any initial lack
of clarity about how interfaces are used.

You should also know that I made the choice to use "older" style Java. This is
primarily to make it familiar to evaluators and secondarily because I didn't
want to get bogged down in _which_ new features to use where, when, and why.

Also, there are many valid approaches to code organization, layering,
paradigm-orientation, etc.. I took the approach that I did because I think it is
clear and easy to follow even when unfamiliar.

## TODOs and Comments

I took a pretty sparse approach to commenting, despite the invitation in the
instructions. This is because most everyone is friendly to _so-called_
"clean" code, but the spectrum of opinions on what comments are worth it when
and when and how those values change for a technical assignment is broad, and
I've been bitten before.

This also means I didn't leave in many TODO comments despite certainly having in
mind a number of things that could be improved, discovered, or clarified.

### ExampleClient.performCallbackRequest

This includes javadoc _about_ the commented-out implementation within.
`example.com/request` is a 404, so if you want to run the application, this is
the state the code must be in. If, however, you want to run tests, the tests
expected that implementation to be present, so it needs to be un-commented for
test runs. I believe this conforms to the guidance provided.

### DocumentRequestEndpointDbIntegrationTests.doesNotPersistDocumentRequestWhenCallbackRequestFails

This test doesn't contain a "TODO", but is disabled, so kind of "dead" in a
commented-out way.

It's been a while since I last worked in Java and contended with the
complexities of Spring configuration. I used this test to drive some
implementation (I made it pass
using `TransactionAspectSupport.currentTransactionStatus().createSavepoint()`),
but then other tests were failing because they had no transaction manager in
context. Of course, I could make everything a `@SpringBootTest`, but that would
dramatically slow down the suite by making _everything_ an integration test (for
some values of "integration test"). I couldn't wrangle things to work _and_ be
fast. If, however, I used `@Transactional` (as the service method now has),
which ought to work at runtime, all the other tests would be fine, but this one
would fail. I chose to leave things there; it's an assignment, not a production
application.

## Oops
I literally just noticed that I missed, "...as well as timestamps for when it 
was created and when the latest update occurred" in the specification of the 
GET endpoint. I'm done, though. For the record, I'd add these pieces to some 
tests, then add `created` and `modified` fields to the `DocumentRequest` 
entity and ensure that the repository's update method updates `modified`, as 
well. If I were to have done so, you'd also see a `TestClock` class and 
distinct tests for modification and creation timing; I prefer to inject 
clocks into things like repositories where time matters so that I can have 
the referential transparency necessary to prove the expected behaviors. If 
it matters to have this data and use or return it somewhere, it matters that 
it's correct.

# To The Assignment Designers
I'll be honest, this took me more time than anyone anticipated. I batted it 
around when I had spare time over a few days, and it probably added up to 
several hours.

It's possible that this simply means I would not be the caliber of Java 
(with Spring, specifically) programmer you're seeking, as of their hire date,
that is. It's also possible that I do meet your needs, but that some 
characteristics of this assignment run the risk of impacting other 
competitive candidates in this way.

In the former case, I would've liked to be able to cut myself off sooner, 
but I would've felt like sending an assignment with some mostly-baked Spring 
Boot configuration and boilerplate and a couple tests for a half-implemented 
endpoint would've provided an inaccurate picture of my capabilities. 
Additionally, the quote, "We don't want to...encourage a trade-off of 
quality for quantity," stood out to me; if I shouldn't sacrifice quality (of 
code) or quantity (of implemented endpoints), I would have to keep going. 
That, however, conflicts with the spirit of the first clause, "We don't want 
to waste your time..."

In the latter case, I think it would be in everyone's best interest to 
identify the characteristics that would lead desirable candidates to 
overwork (or to give up, as many who do not have the flexibility of time 
that I do might).

My personal reflection on this is that both cases could be helped by 
providing a skeleton of the project to candidates first. I spent well over 
half my time first trying to do this without Spring, then trying to pretend 
that everything I wanted would come out of the Spring Boot box, then reading 
documentation and tips on what adjustments to make where, then restarting 
with an entirely different configuration approach, and then eventually 
getting the right dependencies and annotations to have an in-memory database 
with an ORM and the ability to test with and without Spring integration and
database integration, and the ability to test HTTP endpoints. On a 
day-to-day basis, this work is mostly done once and never touched again, so 
it isn't generally reflective of the skills that are most needed on most 
days. Well, that's my opinion. If you share it, I think that it would be 
wise to try to eliminate those struggles.

## Smaller Things
### AWS
> However, the
primary tools you'll be expected to use at this job are Java and the AWS technology stack (including SQL). Displaying familiarity with these
is useful and encouraged.

I don't know how to demonstrate familiarity with AWS in something like this. 
I would not be invested in configuring test accounts and such on sometthing 
like this, particularly not after the rest of it.

### REST
> follow the REST conventions for methods

> GET /status/{id}

In my mind, these conflict. REST conventions direct the use of "resources", 
so a RESTful endpoint that retrieves a record by ID would be more like `GET 
/documents/{id}`. If the direction is simply checking to see if people 
understand the conventional relationships between CRUD operations and HTTP 
verbs, well, I think that could be expressed differently. Or asked in a 
follow-up.