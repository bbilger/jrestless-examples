package com.jrestless.aws.examples;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

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

	@Inject
	private BackendService apiService;

	@GET
	@Path("/info")
	public Response getInfo() {
		return Response.ok(apiService.getInfo()).build();
	}

	@GET
	@Path("/pathparam/{value}")
	public Response getSomePathParam(@PathParam("value") String value) {
		return Response.ok(apiService.getPathParam(value)).build();
	}

	@GET
	@Path("/queryparam")
	public Response getQueryParam(@QueryParam("value") String value) {
		return Response.ok(apiService.getQueryParam(value)).build();
	}

	@GET
	@Path("/service-request")
	public Response reflectGatewayRequest() {
		return Response.ok(apiService.getServiceRequest()).build();
	}

	@GET
	@Path("/lambda-context")
	public Response reflectGatewayIdentity() {
		return Response.ok(apiService.getLambdaContext()).build();
	}

	@POST
	@Path("/post")
	public Response postJson(PojoDto dto) {
		return Response.ok(apiService.post(dto)).build();
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
}
