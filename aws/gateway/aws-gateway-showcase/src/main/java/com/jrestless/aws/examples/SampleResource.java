package com.jrestless.aws.examples;

import java.net.URI;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jrestless.aws.gateway.io.GatewayRequest;

/**
 * A sample resource.
 *
 * @author Bjoern Bilger
 *
 */
@Path("/api")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON })
public class SampleResource {

	@GET
	@Path("/uris")
	public Response getUris(@Context UriInfo uriInfo) {
		return Response.ok(new BaseAndRequestUri(uriInfo)).build();
	}

	@GET
	@Path("/info")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getInfo() {
		return Response.ok(new JaxbDto("up\nand\nrunning")).build();
	}

	@GET
	@Path("/cookie")
	public Response getDynamicHeader(@DefaultValue("false") @QueryParam("bad") boolean bad) {
		int statusCode = bad ? Status.BAD_REQUEST.getStatusCode() : Status.OK.getStatusCode();
		return Response.status(statusCode).entity(new PojoDto("something")).cookie(new NewCookie("foo", "bar"))
				.build();
	}

	@GET
	@Path("/moved")
	public Response getNonDefaultHeader() {
		return Response.status(Status.MOVED_PERMANENTLY).header("Location", "http://google.com").build();
	}

	@GET
	@Path("/pathparam/{value}")
	public Response getSomePathParam(@PathParam("value") String value) {
		return Response.ok(new PojoDto(value)).build();
	}

	@GET
	@Path("/queryparam")
	public Response getQueryParam(@QueryParam("value") String value) {
		return Response.ok(new PojoDto(value)).build();
	}

	@GET
	@Path("/gateway-request")
	public Response reflectGatewayRequest(@Context GatewayRequest gatewayRequest) {
		return Response.ok(gatewayRequest).build();
	}

	@GET
	@Path("/lambda-context")
	public Response reflectGatewayIdentity(@Context com.amazonaws.services.lambda.runtime.Context lambdaContext) {
		return Response.ok(lambdaContext).build();
	}

	@POST
	@Path("/post1")
	public Response postJson(PojoDto dto) {
		return Response.ok(dto).build();
	}

	@POST
	@Path("/post2")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response postJsonOrXml(JaxbDto dto) {
		return Response.ok(dto).build();
	}

	public static class PojoDto {
		private final String value;
		@JsonCreator
		public PojoDto(@JsonProperty("value") String value) {
			this.value = value;
		}
		public String getValue() {
			return value;
		}
	}

	public static class BaseAndRequestUri {

		private final String baseUri;
		private final String requestUri;
		BaseAndRequestUri(String baseUri, String requestUri) {
			this.baseUri = baseUri;
			this.requestUri = requestUri;
		}
		BaseAndRequestUri(URI baseUri, URI requestUri) {
			this(baseUri.toString(), requestUri.toString());
		}
		BaseAndRequestUri(UriInfo uriInfo) {
			this(uriInfo.getBaseUri(), uriInfo.getRequestUri());
		}
		public String getBaseUri() {
			return baseUri;
		}
		public String getRequestUri() {
			return requestUri;
		}
	}

	@XmlRootElement
	public static class JaxbDto {
		private String value;

		@SuppressWarnings("unused")
		private JaxbDto() {
			// for JAXB
		}

		public JaxbDto(String value) {
			this.value = value;
		}
		@XmlElement
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
	}
}
