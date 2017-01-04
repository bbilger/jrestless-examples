# AWS API Gateway Guice Example

Example showing how to use Guice in JRestless.

Note: Guice can be used in every handler type (service, sns, apigw)!

## Installation & Deployment

```bash
git clone https://github.com/bbilger/jrestless-examples.git
cd jrestless-examples
./gradlew build
cd aws/gateway/aws-gateway-guice
serverless deploy
serverless logs -f "api" -t # if you want to tail the logs
```

## Endpoints

|Endpoints                   |Method|Consumes|Produces  | Comment
|----------------------------|------|--------|----------|---
|api/hello                   |GET   |-       |text      | increments a counter in a singleton Guice service and includes its value in the response
|api/goodbye                 |GET   |-       |text      | increments a counter in a "normal" Guice object which obviously has no effect since the service is recreated for each request
|api/hello-goodbye-javax       |GET   |-       |text      | combines hello and goodbye services using javax.inject.Inject to inject the hello service into the goodbye service
|api/hello-goodbye-guice     |GET   |-       |text      | combines hello and goodbye services using com.google.inject.Inject to inject the hello service into the goodbye service
