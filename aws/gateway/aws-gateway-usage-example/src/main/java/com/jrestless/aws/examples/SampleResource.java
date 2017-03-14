package com.jrestless.aws.examples;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/sample")
public class SampleResource {
	@GET
	@Path("/health")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getHealthStatus() {
		return Response.ok(new HealthStatusResponse("up and running")).build();
	}
	static class HealthStatusResponse {
		private final String statusMessage;
		HealthStatusResponse(String statusMessage) {
			this.statusMessage = statusMessage;
		}
		public String getStatusMessage() {
			return statusMessage;
		}
	}
}
