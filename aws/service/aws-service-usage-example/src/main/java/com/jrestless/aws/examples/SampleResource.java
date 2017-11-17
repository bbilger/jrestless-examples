package com.jrestless.aws.examples;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.jrestless.aws.service.io.ServiceRequest;

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

	private final ObjectMapper mapper;

	public SampleResource() {
		mapper = new ObjectMapper().configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
	}

	@GET
	@Path("/info")
	public Response getInfo() {
		return Response.ok(new PojoDto("up\nand\nrunning")).build();
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
	@Path("/service-request")
	public Response reflectGatewayRequest(@Context ServiceRequest serviceRequest) {
		return Response.ok(new PojoDto(serviceRequest.toString())).build();
	}

	@GET
	@Path("/lambda-context")
	public Response reflectGatewayIdentity(@Context com.amazonaws.services.lambda.runtime.Context lambdaContext)
			throws JsonProcessingException {
		return Response.ok(new PojoDto(mapper.writeValueAsString(lambdaContext))).build();
	}

	@POST
	@Path("/post")
	public Response postJson(PojoDto dto) {
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
}
