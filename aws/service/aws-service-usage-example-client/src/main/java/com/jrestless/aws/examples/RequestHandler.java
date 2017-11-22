package com.jrestless.aws.examples;

import javax.annotation.Nullable;

import org.glassfish.jersey.server.ResourceConfig;
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
		// configure the application with the resource + bind the backendService
		init(new ResourceConfig().register(GatewayFeature.class).register(BackendServiceFactory.createBinder())
				.packages("com.jrestless.aws.examples"));
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

	@Override
	public GatewayResponse onRequestFailure(Exception e, GatewayRequestAndLambdaContext request,
			@Nullable JRestlessContainerRequest containerRequest) {
		LOG.error("request handling failed: " + request.getGatewayRequest(), e);
		return super.onRequestFailure(e, request, containerRequest);
	}
}
