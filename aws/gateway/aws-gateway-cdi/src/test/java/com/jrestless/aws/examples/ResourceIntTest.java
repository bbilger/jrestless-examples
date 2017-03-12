package com.jrestless.aws.examples;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.amazonaws.services.lambda.runtime.Context;
import com.jrestless.aws.gateway.io.DefaultGatewayRequest;
import com.jrestless.aws.gateway.io.DefaultGatewayRequestContext;
import com.jrestless.aws.gateway.io.GatewayResponse;

public class ResourceIntTest {

	private RequestHandler handler;
	private Context context = Mockito.mock(Context.class);

	@Before
	public void setup() {
		handler = new RequestHandler();
	}

	@Test
	public void testHelloServiceIsSingletonService() {
		DefaultGatewayRequest request = createRequest("/api/hello");
		GatewayResponse response0 = handler.handleRequest(request, context);
		GatewayResponse response1 = handler.handleRequest(request, context);
		assertEquals("Hello, #0", response0.getBody());
		assertEquals("Hello, #1", response1.getBody());
	}

	@Test
	public void testHelloServiceIsRequestScopedService() {
		DefaultGatewayRequest request = createRequest("/api/goodbye");
		GatewayResponse response0 = handler.handleRequest(request, context);
		GatewayResponse response1 = handler.handleRequest(request, context);
		assertEquals("Goodbye, #0", response0.getBody());
		assertEquals("Goodbye, #0", response1.getBody());
	}

	@Test
	public void testHelloAndGoodbyeWithBothInjections() {
		DefaultGatewayRequest requestJavax = createRequest("/api/hello-goodbye");
		GatewayResponse responseJavax0 = handler.handleRequest(requestJavax, context);
		GatewayResponse responseJavax1 = handler.handleRequest(requestJavax, context);

		assertEquals("Hello, #0\nGoodbye, #0", responseJavax0.getBody());
		assertEquals("Hello, #1\nGoodbye, #0", responseJavax1.getBody());
	}

	private DefaultGatewayRequest createRequest(String path) {
		DefaultGatewayRequest request = new DefaultGatewayRequest();
		DefaultGatewayRequestContext requestContext = new DefaultGatewayRequestContext();
		request.setRequestContext(requestContext);
		request.setHttpMethod("GET");
		request.setPath(path);
		return request;
	}
}
