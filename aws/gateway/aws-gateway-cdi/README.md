# AWS API Gateway CDI Example

Example showing how to use CDI/Weld in JRestless.

Note: CDI/Weld can be used in every handler type (service, sns, apigw)!

## Installation & Deployment

```bash
git clone https://github.com/bbilger/jrestless-examples.git
cd jrestless-examples
./gradlew build
cd aws/gateway/aws-gateway-cdi
serverless deploy
serverless logs -f "api" -t # if you want to tail the logs
```

## Endpoints

|Endpoints                   |Method|Consumes|Produces  | Comment
|----------------------------|------|--------|----------|---
|api/hello                   |GET   |-       |text      | increments a counter in a application-scoped CDI service and includes its value in the response
|api/goodbye                 |GET   |-       |text      | increments a counter in a request-scoped CDI object which obviously has no effect since the service is recreated for each request
|api/hello-goodbye       |GET   |-       |text      | combines hello and goodbye services using javax.inject.Inject to inject the hello service into the goodbye service

