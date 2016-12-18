package com.jrestless.aws.examples;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A sample resource.
 *
 * @author Bjoern Bilger
 *
 */
@Path("/")
@Consumes({ MediaType.APPLICATION_JSON })
public class SampleResource {

	private static final Logger LOG = LoggerFactory.getLogger(SampleResource.class);

	@POST
	@Path("/entity-created")
	public void entityCreatedRoot(Entity entity) {
		LOG.info("hit entity created; no id passed; received: " + entity);
	}

	@POST
	@Path("/entity-created/{id}")
	public void entityCreated(@PathParam("id") String id, Entity entity) {
		LOG.info("hit entity created; passed id: '" + id + "'; received: " + entity);
	}

	@POST
	@Path("/entity-deleted")
	public void entityDeletedRoot(Entity entity) {
		LOG.info("hit entity deleted; no id passed; received: " + entity);
	}

	@POST
	@Path("/entity-deleted/{id}")
	public void entityDeleted(@PathParam("id") String id, Entity entity) {
		LOG.info("hit entity deleted; passed id: '" + id + "'; received: " + entity);
	}

	public static class Entity {
		private final String value;
		@JsonCreator
		public Entity(@JsonProperty("value") String value) {
			this.value = value;
		}
		public String getValue() {
			return value;
		}
		@Override
		public String toString() {
			return "Entity [value=" + value + "]";
		}
	}

}
