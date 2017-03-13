package com.jrestless.aws.examples;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Bjoern Bilger
 *
 */
@Path("/api")
@Consumes({ MediaType.TEXT_PLAIN })
@Produces({ MediaType.TEXT_PLAIN })
public class ApiResource {
	@GET
	public Response get() {
		return Response.ok("get").build();
	}
	@POST
	public Response post(String requestBody) {
		return Response.ok("posted: " + requestBody).build();
	}
	@PUT
	public Response put(String requestBody) {
		return Response.ok("put: " + requestBody).build();
	}
	@DELETE
	public Response delete() {
		return Response.ok("delete").build();
	}
	@OPTIONS
	public Response options() {
		return Response.ok("options").build();
	}
	@PATCH
	public Response patch() {
		/*
		 * this is reachable from the same origin, only since the CORS filter
		 * registered at aws-gateway-cors-frontend does not allow this
		 */
		return Response.ok("patch").build();
	}
}
