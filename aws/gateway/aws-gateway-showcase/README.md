# AWS API Gateway Showcase

Example showing JRestless' features.

## Installation & Deployment

```bash
git clone https://github.com/bbilger/jrestless-examples.git
cd jrestless-examples
./gradlew build
cd aws/gateway/aws-gateway-showcase
serverless deploy
serverless logs -f "api" -t # if you want to tail the logs
```

## Endpoints

|Endpoints                   |Method|Consumes |Produces  | Status Code | Comment
|----------------------------|------|---------|----------|-------------|---
|api/uris                    |GET   |-        |JSON      |200          | returns the base and request URI for this request
|api/info                    |GET   |-        |XML, JSON |200          | responds with a static body
|api/cookie?bad=[true/false] |GET   |-        |JSON      |200, 400     | responds either with 200 or a 400 depending on the query parameter `bad`, sets a cookie header and includes a body
|api/moved                   |GET   |-        |-         |301          | responds with a 301 and a `Location` header
|api/pathparam/{value}       |GET   |-        |JSON      |200          | responds with a body including the path parameter
|api/queryparam?value=...    |GET   |-        |JSON      |200          | responds with a body including the query parameter
|api/gateway-request         |GET   |-        |JSON      |200          | responds with the original request made by AWS API Gateway to the Lambda function - showing how to inject it into a JAX-RS endpoint
|api/lambda-context          |GET   |-        |JSON      |200          | responds with the request's lambda context - showing how to inject it a JAX-RS endpoint
|api/post1                   |GET   |JSON     |JSON      |200          | responds with the request body (`{"value": "..."}`)
|api/post2                   |GET   |JSON, XML|JSON,XML  |200          | responds with the request body (`{"value": "..."}`, `<jaxbDto><value>...</value></jaxbDto>`)
|api/application-exception   |GET   |-        |JSON      |500          | showcase a specific exception mapper
|api/global-exception        |GET   |-        |JSON      |500          | showcase a non-specific auto-discovered exception mapper
