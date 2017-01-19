# AWS Lambda Service Function Usage Example

Example showing JRestless' _Lambda service function_ features.

All requests made through API Gateway or rather `aws-service-usage-example-client` are delegated to the _Lambda service function_ `aws-service-usage-example`.

**Attention**: If you deploy `aws/service/aws-service-usage-example` to a region different than `eu-central-1`, then please make sure to adjust the region in the client, as well: https://github.com/bbilger/jrestless-examples/blob/master/aws/service/aws-service-usage-example-client/src/main/java/com/jrestless/aws/examples/BackendServiceFactory.java#L27 (don't forget to re-built and re-deploy the client function, afterwards)

## Installation & Deployment

```bash
git clone https://github.com/bbilger/jrestless-examples.git
cd jrestless-examples
./gradlew build
cd aws/service/aws-service-usage-example
serverless deploy
cd ../aws-service-usage-example-client
serverless deploy
serverless logs -f "api" -t # if you want to tail the logs
```

## Endpoints

|Endpoints                   |Method|Consumes |Produces  | Status Code | Comment
|----------------------------|------|---------|----------|-------------|---
|api/info                    |GET   |-        |JSON      |200          | responds with a static body
|api/pathparam/{value}       |GET   |-        |JSON      |200          | responds with a body including the path parameter
|api/queryparam?value=...    |GET   |-        |JSON      |200          | responds with a body including the query parameter
|api/service-request         |GET   |-        |JSON      |200          | responds with the original request made to the _Lambda service function_ - showing how to inject it into a JAX-RS endpoint
|api/lambda-context          |GET   |-        |JSON      |200          | responds with the request's lambda context - showing how to inject it a JAX-RS endpoint
|api/post                    |GET   |JSON     |JSON      |200          | responds with the request body (`{"value": "..."}`)
