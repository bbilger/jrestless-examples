package com.jrestless.aws.examples;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Global exception mapper that is used when there's no more specific exception
 * mapper.
 */
@Provider
public class GlobalExceptionMapper implements ExceptionMapper<Exception> {
	@Override
	public Response toResponse(Exception exception) {
		return Response.status(Status.INTERNAL_SERVER_ERROR)
				.entity(new ErrorResponse(GlobalExceptionMapper.class.getSimpleName())).build();
	}
}
