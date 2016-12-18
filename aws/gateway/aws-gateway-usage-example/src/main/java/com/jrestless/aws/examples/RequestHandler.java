package com.jrestless.aws.examples;

import org.glassfish.jersey.server.ResourceConfig;

import com.jrestless.aws.gateway.GatewayFeature;
import com.jrestless.aws.gateway.handler.GatewayRequestObjectHandler;

/**
 * The request handler as lambda function.
 *
 * @author Bjoern Bilger
 *
 */
public class RequestHandler extends GatewayRequestObjectHandler {
	public RequestHandler() {
		ResourceConfig config = new ResourceConfig()
				.register(GatewayFeature.class)
				.packages("com.jrestless.aws.examples");
		init(config);
		start();
	}
}
