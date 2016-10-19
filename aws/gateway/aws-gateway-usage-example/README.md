# AWS Gateway Usage Example

Simple JRestless usage example.

## Installation & Deployment

```bash
git clone https://github.com/bbilger/jrestless-examples.git
cd jrestless-examples
./gradlew build
cd aws/gateway/aws-gateway-usage-example
serverless deploy
```

## Endpoints

|Endpoints                   |Method|Consumes|Produces  | Comment
|----------------------------|------|--------|----------|---
|sample/health               |GET   |-       |XML, JSON |
