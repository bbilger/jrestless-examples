package com.jrestless.aws.examples;

import java.util.List;

import org.apache.log4j.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.bridge.SLF4JBridgeHandler;

import com.amazonaws.services.lambda.runtime.Context;
import com.jrestless.aws.service.ServiceResourceConfig;
import com.jrestless.aws.service.handler.ServiceRequestAndLambdaContext;
import com.jrestless.aws.service.handler.ServiceRequestObjectHandler;
import com.jrestless.aws.service.io.ServiceRequestImpl;
import com.jrestless.aws.service.io.ServiceResponse;
import com.jrestless.core.container.io.JRestlessContainerRequest;

/**
 * The request handler as lambda function.
 *
 * @author Bjoern Bilger
 *
 */
public class RequestHandler extends ServiceRequestObjectHandler {

	private static final Logger LOG = LoggerFactory.getLogger(RequestHandler.class);

	public RequestHandler() {
		// bridge java.util.logging (used by Jersey) to SLF4J which will use log4j
		SLF4JBridgeHandler.removeHandlersForRootLogger();
		SLF4JBridgeHandler.install();
		// configure the application with the resource
		init(new ServiceResourceConfig().packages("com.jrestless.aws.examples"));
		start();
	}

	@Override
	public ServiceResponse handleRequest(ServiceRequestImpl request, Context lambdaContext) {
		// set the mapped diagnostic context to be able to log the base/original AWS request id
		if (request != null && request.getHeaders() != null) {
			List<String> awsRequestIdHeaderValues = request.getHeaders().get("X-Base-Aws-Request-Id");
			if (awsRequestIdHeaderValues != null && !awsRequestIdHeaderValues.isEmpty()) {
				MDC.put("BaseAWSRequestId", awsRequestIdHeaderValues.get(0));
			}
		}
		try {
			return super.handleRequest(request, lambdaContext);
		} finally {
			MDC.remove("BaseAWSRequestId");
		}
	}

	@Override
	public void beforeHandleRequest(ServiceRequestAndLambdaContext request,
			JRestlessContainerRequest containerRequest) {
		LOG.info("start to handle request: " + request.getServiceRequest());
	}

	@Override
	public ServiceResponse onRequestSuccess(ServiceResponse response, ServiceRequestAndLambdaContext request,
			JRestlessContainerRequest containerRequest) {
		LOG.info("request handled successfully: " + response);
		return response;
	}
}
