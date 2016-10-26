package com.jrestless.aws.examples;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.glassfish.hk2.api.Factory;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.hk2.utilities.Binder;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.AWSLambdaClient;
import com.amazonaws.services.lambda.runtime.Context;
import com.jrestless.aws.service.client.FeignLambdaServiceInvokerClient;
import com.jrestless.aws.service.client.LambdaServiceFunctionTarget;

import feign.Feign;
import feign.Request;
import feign.RequestTemplate;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.slf4j.Slf4jLogger;

public class BackendServiceFactory implements Factory<BackendService> {

	private static final String BACKEND_SERVICE_FUNCTION_NAME = "aws-service-usage-example-dev-api";
	private static final Regions BACKEND_SERVICE_REGION = Regions.EU_CENTRAL_1;

	private final BackendService backendService;
	private final AWSLambdaClient awsLambdaClient;

	@Inject
	public BackendServiceFactory(ServiceLocator serviceLocator) {
		awsLambdaClient = new AWSLambdaClient();
		awsLambdaClient.configureRegion(BACKEND_SERVICE_REGION);
		backendService = Feign.builder()
				.client(FeignLambdaServiceInvokerClient.builder()
						.setRegion(BACKEND_SERVICE_REGION)
						.setFunctionName(BACKEND_SERVICE_FUNCTION_NAME)
						.build())
				.decoder(new JacksonDecoder())
				.encoder(new JacksonEncoder())
				.logger(new Slf4jLogger())
				.target(new LambdaServiceFunctionTarget<BackendService>(BackendService.class) {
					@Override
					public Request apply(RequestTemplate input) {
						// TODO inject the context directly => requires the context to be bound as proxy
						Context lambdaContext = serviceLocator.getService(Context.class);
						// propagate the AWS request ID => the called service can log the original AWS request ID
						input.header("X-Base-Aws-Request-Id", lambdaContext.getAwsRequestId());
						return super.apply(input);
					}
				});
	}

	@Override
	public BackendService provide() {
		return backendService;
	}

	@Override
	public void dispose(BackendService instance) {
		awsLambdaClient.shutdown();
	}

	public static Binder createBinder() {
		return new AbstractBinder() {
			@Override
			protected void configure() {
				bindFactory(BackendServiceFactory.class).to(BackendService.class).in(Singleton.class);
			}
		};
	}
}
