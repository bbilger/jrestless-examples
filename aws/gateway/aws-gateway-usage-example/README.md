# AWS API Gateway Usage Example

Simple JRestless usage example.

## Installation & Deployment

```bash
git clone https://github.com/bbilger/jrestless-examples.git
cd jrestless-examples
./gradlew build
cd aws/gateway/aws-gateway-usage-example
serverless deploy
serverless logs -f "sample" -t # if you want to tail the logs
```

## Endpoints

|Endpoints                   |Method|Consumes|Produces  | Comment
|----------------------------|------|--------|----------|---
|sample/health               |GET   |-       |JSON      |
