package com.jrestless.aws.examples;

import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jrestless.aws.gateway.GatewayFeature;
import com.jrestless.aws.gateway.handler.GatewayRequestAndLambdaContext;
import com.jrestless.aws.gateway.handler.GatewayRequestObjectHandler;
import com.jrestless.aws.gateway.io.GatewayResponse;
import com.jrestless.core.container.io.JRestlessContainerRequest;
import com.jrestless.core.filter.cors.CorsFilter;

/**
 * The request handler as lambda function.
 *
 * @author Bjoern Bilger
 *
 */
public class RequestHandler extends GatewayRequestObjectHandler {

	private static final Logger LOG = LoggerFactory.getLogger(RequestHandler.class);

	public RequestHandler() {
		/*
		 * By default max age is set to 3600, the methods GET, POST, PUT,
		 * DELETE, OPTIONS, HEAD are allowed, all headers will be allowed (since
		 * none are set), credentials are allowed, no headers are exposed,
		 * {@link DefaultSameOriginPolicy} is used and all origins are allowed.
		 */
		CorsFilter corsFilter = new CorsFilter.Builder()
				.build();
		ResourceConfig config = new ResourceConfig()
				.register(GatewayFeature.class)
				.register(corsFilter)
				.packages("com.jrestless.aws.examples");
		init(config);
		start();
	}

	@Override
	protected void beforeHandleRequest(GatewayRequestAndLambdaContext request,
			JRestlessContainerRequest containerRequest) {
		LOG.info("start to handle request for http method: " + request.getGatewayRequest().getHttpMethod());
	}

	@Override
	protected GatewayResponse onRequestSuccess(GatewayResponse response, GatewayRequestAndLambdaContext request,
			JRestlessContainerRequest containerRequest) {
		LOG.info("request handled successfully: " + response);
		return response;
	}
}
