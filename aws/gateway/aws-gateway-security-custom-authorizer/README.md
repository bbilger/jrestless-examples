# AWS API Gateway Custom Authorizer

Example showing the integration of a custom authorizer.

## Installation & Deployment

```bash
git clone https://github.com/bbilger/jrestless-examples.git
cd jrestless-examples
./gradlew build
cd aws/gateway/aws-gateway-security-custom-authorizer
serverless deploy
serverless logs -f "api" -t # if you want to tail the logs

curl -v --header "Authorization: allow" .../dev/api/private # 200
curl -v --header "Authorization: deny" .../dev/api/private # 401
curl -v --header "Authorization: unauthorized" .../dev/api/private # 403
curl -v --header "Authorization: fail" .../dev/api/private # 500
curl -v .../dev/api/private # 403
```

## Endpoints

|Endpoints                   |Method|Consumes |Produces  | Status Code    | Comment
|----------------------------|------|---------|----------|----------------|---
|api/private                 |GET   |-        | JSON     |200,401,403,500 | Authorization Header set to: "allow"=>200, "deny"=>403, "unauthorized"=>401, anything else=>500; no header=>401
|api/public                  |GET   |-        | JSON     |200             | no principal injected
