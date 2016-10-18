package com.jrestless.aws.examples;

import com.jrestless.aws.gateway.GatewayResourceConfig;
import com.jrestless.aws.gateway.handler.GatewayRequestObjectHandler;

/**
 * The request handler as lambda function.
 *
 * @author Bjoern Bilger
 *
 */
public class RequestHandler extends GatewayRequestObjectHandler {
	public RequestHandler() {
		init(new GatewayResourceConfig().packages("com.jrestless.aws.examples"));
		start();
	}
}
