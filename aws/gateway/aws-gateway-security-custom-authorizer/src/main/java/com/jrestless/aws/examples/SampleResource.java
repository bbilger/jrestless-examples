package com.jrestless.aws.examples;

import java.util.Collections;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

import com.jrestless.aws.security.CustomAuthorizerPrincipal;

/**
 * A sample resource.
 *
 * @author Bjoern Bilger
 *
 */
@Path("/api")
@Produces({ MediaType.APPLICATION_JSON })
public class SampleResource {

	@GET
	@Path("/public")
	public CustomAuthorizerPrincipalResponse getPublic(@Context SecurityContext securityContext) {
		// principal == null
		return new CustomAuthorizerPrincipalResponse((CustomAuthorizerPrincipal) securityContext.getUserPrincipal());
	}

	@GET
	@Path("/private")
	public CustomAuthorizerPrincipalResponse getPrivate(@Context SecurityContext securityContext) {
		return new CustomAuthorizerPrincipalResponse((CustomAuthorizerPrincipal) securityContext.getUserPrincipal());
	}

	static class CustomAuthorizerPrincipalResponse {
		private CustomAuthorizerPrincipal principal;
		CustomAuthorizerPrincipalResponse(CustomAuthorizerPrincipal principal) {
			this.principal = principal;
		}
		public boolean isPrincipalSet() {
			return principal != null;
		}
		public String getName() {
			return isPrincipalSet() ? principal.getName() : null;
		}
		public String getPrincipalId() {
			return isPrincipalSet() ? principal.getClaims().getPrincipalId() : null;
		}
		public Object getKey() {
			return getAllClaims().get("key");
		}

		/*
		 * attention: there seems to be a bug in the custom authorizers -> APIGW
		 * "communication" since this comes back as string
		 */
		public Object getNumKey() {
			return getAllClaims().get("numKey");
		}

		/*
		 * attention: there seems to be a bug in the customAuthorizers -> APIGW
		 * "communication" since this comes back as string
		 */
		public Object getBoolKey() {
			return getAllClaims().get("boolKey");
		}
		public Map<String, Object> getAllClaims() {
			if (!isPrincipalSet()) {
				return Collections.emptyMap();
			} else {
				return principal.getClaims().getAllClaims();
			}
		}
	}
}
