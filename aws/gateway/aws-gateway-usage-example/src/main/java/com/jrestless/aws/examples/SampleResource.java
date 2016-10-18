package com.jrestless.aws.examples;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * A sample resource.
 *
 * @author Bjoern Bilger
 *
 */
@Path("/sample")
public class SampleResource {
	@GET
	@Path("/health")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getHealth() {
		return Response.ok(new HealthStatusDto("up and running")).build();
	}

	@XmlRootElement // for JAXB
	public static class HealthStatusDto {
		private String statusMessage;

		@SuppressWarnings("unused")
		private HealthStatusDto() {
			// for JAXB
		}

		HealthStatusDto(String statusMessage) {
			this.statusMessage = statusMessage;
		}

		@XmlElement // for JAXB
		public String getStatusMessage() {
			return statusMessage;
		}
	}
}
