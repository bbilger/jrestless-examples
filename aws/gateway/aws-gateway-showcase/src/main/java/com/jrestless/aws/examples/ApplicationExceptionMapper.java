package com.jrestless.aws.examples;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Exception mapper for all {@link ApplicationException}s.
 */
@Provider
public class ApplicationExceptionMapper implements ExceptionMapper<ApplicationException> {
	@Override
	public Response toResponse(ApplicationException exception) {
		return Response.status(Status.INTERNAL_SERVER_ERROR)
				.entity(new ErrorResponse(ApplicationExceptionMapper.class.getSimpleName())).build();
	}
}
