package com.jrestless.aws.examples;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * A sample resource.
 *
 * @author Bjoern Bilger
 *
 */
@Path("/")
public class UiResource {
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String get() {
		return loadInternalResource("/index.html");
	}

	private static String loadInternalResource(String path) {
		try (InputStream is = UiResource.class.getResourceAsStream(path)) {
			return toString(is);
		} catch (IOException e) {
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
}
