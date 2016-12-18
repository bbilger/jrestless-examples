package com.jrestless.aws.examples;

import java.util.HashMap;
import java.util.Map;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

/*
 * This is similar to the JavaScript example given in the AWS docs:
 * http://docs.aws.amazon.com/apigateway/latest/developerguide/use-custom-authorizer.html
 *
 * This is unrelated to JRestless and I would suggest implementing this in JavaScript in production!
 */
public class CustomAuthorizerHandler implements RequestHandler<Map<String, Object>, Map<String, Object>> {

	@Override
	public Map<String, Object> handleRequest(Map<String, Object> event, Context context) {
		String token = (String) event.get("authorizationToken");
		String resource = (String) event.get("methodArn");
		String principalId = "123";
		switch (token) {
		case "allow":
			return generatePolicy(principalId, "Allow", resource);
		case "deny":
			return generatePolicy(principalId, "Deny", resource);
		case "unauthorized":
			throw new RuntimeException("Unauthorized");
		default:
			throw new RuntimeException("fail");
		}
	}

	private Map<String, Object> generatePolicy(String principalId, String effect, String resource) {
		Map<String, Object> authResponse = new HashMap<>();
		authResponse.put("principalId", principalId);
		Map<String, Object> policyDocument = new HashMap<>();
		policyDocument.put("Version", "2012-10-17"); // default version
		Map<String, String> statementOne = new HashMap<>();
		statementOne.put("Action", "execute-api:Invoke"); // default action
		statementOne.put("Effect", effect);
		statementOne.put("Resource", resource);
		policyDocument.put("Statement", new Object[] {statementOne});
		authResponse.put("policyDocument", policyDocument);
		if ("Allow".equals(effect)) {
			Map<String, Object> context = new HashMap<>();
			context.put("key", "value");
			context.put("numKey", Long.valueOf(1L));
			context.put("boolKey", Boolean.TRUE);
			authResponse.put("context", context);
		}
		return authResponse;
	}
}
