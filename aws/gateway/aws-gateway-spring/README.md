# AWS API Gateway Spring Example

Example showing how to use Spring in JRestless.

Note: Spring can be used in every handler type (service, sns, apigw)!

## Installation & Deployment

```bash
git clone https://github.com/bbilger/jrestless-examples.git
cd jrestless-examples
./gradlew build
cd aws/gateway/aws-gateway-spring
serverless deploy
serverless logs -f "api" -t # if you want to tail the logs
```

## Endpoints

|Endpoints                   |Method|Consumes|Produces  | Comment
|----------------------------|------|--------|----------|---
|api/hello                   |GET   |-       |text      | increments a counter in a normal Spring (singleton) bean and includes its value in the response
|api/goodbye                 |GET   |-       |text      | increments a counter in a prototype Spring bean which obviously has no effect since the bean is recreated for each request
