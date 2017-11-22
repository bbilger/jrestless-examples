package com.jrestless.aws.examples;

import org.glassfish.jersey.server.ResourceConfig;
import org.jboss.weld.environment.se.Weld;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jrestless.aws.gateway.GatewayFeature;
import com.jrestless.aws.gateway.handler.GatewayRequestAndLambdaContext;
import com.jrestless.aws.gateway.handler.GatewayRequestObjectHandler;
import com.jrestless.aws.gateway.io.GatewayResponse;
import com.jrestless.core.container.io.JRestlessContainerRequest;

/**
 * The request handler as lambda function.
 *
 * @author Bjoern Bilger
 *
 */
public class RequestHandler extends GatewayRequestObjectHandler {

	private static final Logger LOG = LoggerFactory.getLogger(RequestHandler.class);

	public RequestHandler() {

		Weld weld = new Weld();
		weld.initialize();

		// configure the application with the resource
		ResourceConfig resourceConfig = new ResourceConfig()
				.register(GatewayFeature.class)
				.packages("com.jrestless.aws.examples");
		init(resourceConfig);
		start();
	}

	@Override
	protected void beforeHandleRequest(GatewayRequestAndLambdaContext request,
			JRestlessContainerRequest containerRequest) {
		LOG.info("start to handle request: " + request.getGatewayRequest());
	}

	@Override
	protected GatewayResponse onRequestSuccess(GatewayResponse response, GatewayRequestAndLambdaContext request,
			JRestlessContainerRequest containerRequest) {
		LOG.info("request handled successfully: " + response);
		return response;
	}
}
