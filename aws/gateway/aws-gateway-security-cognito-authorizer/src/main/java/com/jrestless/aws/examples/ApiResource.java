package com.jrestless.aws.examples;

import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

import com.jrestless.aws.security.CognitoUserPoolAuthorizerPrincipal;

/**
 * A sample resource.
 *
 * @author Bjoern Bilger
 *
 */
@Path("/api")
@Produces({ MediaType.APPLICATION_JSON })
public class ApiResource {

	@GET
	@Path("/public")
	public CognitoUserPoolAuthorizerPrincipalResponse getPublic(@Context SecurityContext securityContext) {
		// principal == null
		return new CognitoUserPoolAuthorizerPrincipalResponse(
				(CognitoUserPoolAuthorizerPrincipal) securityContext.getUserPrincipal());
	}

	@GET
	@Path("/private")
	public CognitoUserPoolAuthorizerPrincipalResponse getPrivate(@Context SecurityContext securityContext) {
		return new CognitoUserPoolAuthorizerPrincipalResponse(
				(CognitoUserPoolAuthorizerPrincipal) securityContext.getUserPrincipal());
	}

	static class CognitoUserPoolAuthorizerPrincipalResponse {
		private CognitoUserPoolAuthorizerPrincipal principal;
		CognitoUserPoolAuthorizerPrincipalResponse(CognitoUserPoolAuthorizerPrincipal principal) {
			this.principal = principal;
		}
		public boolean isPrincipalSet() {
			return principal != null;
		}
		public Map<String, Object> getAllClaims() {
			return isPrincipalSet() ? principal.getClaims().getAllClaims() : null;
		}
	}
}
