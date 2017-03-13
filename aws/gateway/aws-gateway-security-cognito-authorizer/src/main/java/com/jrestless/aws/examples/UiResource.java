package com.jrestless.aws.examples;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import org.glassfish.jersey.server.mvc.Viewable;

@Path("ui")
public class UiResource {

	private static final String AWS_COGNITO_IDENTITY_URL = "https://raw.githubusercontent.com/"
			+ "aws/amazon-cognito-identity-js/master/dist/amazon-cognito-identity.min.js";
	private static final String AWS_COGNITO_SDK_URL = "https://raw.githubusercontent.com/"
			+ "aws/amazon-cognito-identity-js/master/dist/aws-cognito-sdk.min.js";
	private static final String JSBN_URL = "http://www-cs-students.stanford.edu/%7Etjw/jsbn/jsbn.js";
	private static final String JSBN2_URL = "http://www-cs-students.stanford.edu/%7Etjw/jsbn/jsbn2.js";

	@GET
	@Produces(MediaType.TEXT_HTML)
	public Viewable get() {
		return new Viewable("/index.mustache", new IndexModel());
	}

	@GET
	@Path("/auth.js")
	@Produces("text/javascript")
	public String getAuthJs() {
		return loadInternalResource("/auth.js");
	}

	// couldn't find a CDN and github blocks requests with accept header "text/javascript" => load it here
	@GET
	@Path("/amazon-cognito-identity.min.js")
	@Produces("text/javascript")
	public String getAmazonCognitoIdentityJs() {
		return loadExternalResource(AWS_COGNITO_IDENTITY_URL);
	}

	// couldn't find a CDN and github blocks requests with accept header "text/javascript" => load it here
	@GET
	@Path("/aws-cognito-sdk.min.js")
	@Produces("text/javascript")
	public String getAmazonCognitoSdkJs() {
		return loadExternalResource(AWS_COGNITO_SDK_URL);
	}

	// avoid mixed content error
	@GET
	@Path("/jsbn.js")
	@Produces("text/javascript")
	public String getJsbnJs() {
		return loadExternalResource(JSBN_URL);
	}

	// avoid mixed content error
	@GET
	@Path("/jsbn2.js")
	@Produces("text/javascript")
	public String getJsbn2Js() {
		return loadExternalResource(JSBN2_URL);
	}

	private static String loadInternalResource(String path) {
		try (InputStream is = UiResource.class.getResourceAsStream(path)) {
			return toString(is);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private static String loadExternalResource(String url) {
		try {
			URL randomCatUrl = new URL(url);
			HttpURLConnection httpConnection = (HttpURLConnection) randomCatUrl.openConnection();
			if (httpConnection.getResponseCode() == Status.OK.getStatusCode()) {
				try (InputStream is = httpConnection.getInputStream()) {
					return toString(is);
				}
			} else {
				String msg = "failed to external resource '" + url + "' " + httpConnection.getResponseCode();
				throw new RuntimeException(msg);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private static String toString(InputStream is) {
		try (BufferedReader br = new BufferedReader(
				new InputStreamReader(is, StandardCharsets.UTF_8))) {
			return br.lines().collect(Collectors.joining("\n"));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static class IndexModel {
		private final String userPoolId;
		private final String clientId;
		IndexModel() {
			this.userPoolId = System.getenv("USER_POOL_ID");
			this.clientId = System.getenv("CLIENT_ID");
		}
		public String getUserPoolId() {
			return userPoolId;
		}
		public String getClientId() {
			return clientId;
		}
	}
}
