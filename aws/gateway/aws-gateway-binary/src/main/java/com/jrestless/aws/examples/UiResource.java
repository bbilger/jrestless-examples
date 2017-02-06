package com.jrestless.aws.examples;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.jrestless.aws.gateway.io.GatewayRequest;

@Path("/ui")
public class UiResource {

	private static final String UPLOAD_UI = ""
			+ "<!DOCTYPE html>"
			+ "<html>%n"
			+ "<body>%n"
			+ "<form action=\"%s\" method=\"post\" enctype=\"multipart/form-data\">%n"
			+ "<p>%n"
			+ "Select a file : <input type=\"file\" name=\"file\" />%n"
			+ "</p>%n"
			+ "<input type=\"submit\" value=\"Upload It\" />%n"
			+ "</form>%n"
			+ "</body>%n"
			+ "</html>%n";


	@GET
	@Path("/upload")
	@Produces(MediaType.TEXT_HTML)
	public String getUploadUi(@Context GatewayRequest request) {
		return String.format(UPLOAD_UI, "/" + request.getRequestContext().getStage() + "/api/upload");
	}
}
