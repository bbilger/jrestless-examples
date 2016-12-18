package com.jrestless.aws.examples;

import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.bridge.SLF4JBridgeHandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jrestless.aws.sns.SnsFeature;
import com.jrestless.aws.sns.handler.SnsRecordAndLambdaContext;
import com.jrestless.aws.sns.handler.SnsRequestObjectHandler;
import com.jrestless.core.container.io.JRestlessContainerRequest;

/**
 * The request handler as lambda function.
 *
 * @author Bjoern Bilger
 *
 */
public class RequestHandler extends SnsRequestObjectHandler {

	private static final Logger LOG = LoggerFactory.getLogger(RequestHandler.class);
	private final ObjectMapper objectMapper = new ObjectMapper();

	public RequestHandler() {
		// bridge java.util.logging (used by Jersey) to SLF4J which will use log4j
		SLF4JBridgeHandler.removeHandlersForRootLogger();
		SLF4JBridgeHandler.install();
		// configure the application with the resource
		ResourceConfig config = new ResourceConfig()
				.register(SnsFeature.class)
				.packages("com.jrestless.aws.examples");
		init(config);
		start();
	}

	@Override
	protected void beforeHandleRequest(SnsRecordAndLambdaContext request,
			JRestlessContainerRequest containerRequest) {
		try {
			LOG.info("start to handle request: " + objectMapper.writeValueAsString(request.getSnsRecord()));
		} catch (JsonProcessingException e) {
			LOG.error("failed to serialize sns record", e);
		}
	}
}
