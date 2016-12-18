package com.jrestless.aws.examples;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import javax.activation.DataSource;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.StreamingOutput;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * A sample resource.
 *
 * @author Bjoern Bilger
 *
 */
@Path("/api")
public class SampleResource {

	private static final Logger LOG = LoggerFactory.getLogger(SampleResource.class);
	private static final int BUFFER_LENGTH = 1024;
	private static final int NR_RETRIES  = 3;

	@GET
	@Path("/cat-input-stream")
	@Produces("image/gif")
	public InputStream getRandomCatAsInputStream() {
		return loadRandomCatGif();
	}

	@GET
	@Path("/cat-byte-array")
	@Produces("image/gif")
	public byte[] getRandomCatAsByteArray() {
		return toByteArray(loadRandomCatGif());
	}

	@GET
	@Path("/cat-streaming-output")
	@Produces("image/gif")
	public StreamingOutput getRandomCatAsStreamingOutput() {
		return new StreamingOutput() {
			@Override
			public void write(OutputStream os) throws IOException, WebApplicationException {
				try (InputStream is = loadRandomCatGif()) {
					byte[] buffer = new byte[BUFFER_LENGTH];
					int bytesRead;
					while ((bytesRead = is.read(buffer)) != -1) {
						os.write(buffer, 0, bytesRead);
					}
				}
			}
		};
	}

	@GET
	@Path("/cat-data-source")
	@Produces("image/gif")
	public DataSource getRandomCatAsDataSource() {
		InputStream is = loadRandomCatGif();
		return new DataSource() {
			@Override
			public OutputStream getOutputStream() throws IOException {
				return null;
			}
			@Override
			public String getName() {
				return null;
			}
			@Override
			public InputStream getInputStream() throws IOException {
				return is;
			}
			@Override
			public String getContentType() {
				return "image/gif";
			}
		};
	}

	@GET
	@Path("/cat-file")
	@Produces("image/gif")
	public File getRandomCatAsFile() {
		try {
			java.nio.file.Path tmpFile = Files.createTempFile("cat", ".gif");
			try (InputStream is = loadRandomCatGif()) {
				Files.copy(is, tmpFile, StandardCopyOption.REPLACE_EXISTING);
			}
			return tmpFile.toAbsolutePath().toFile();
		} catch (IOException e) {
			LOG.error("failed to create/write temporary file", e);
			throw new RuntimeException(e);
		}
	}

	@POST
	@Path("/binary")
	@Consumes(MediaType.APPLICATION_OCTET_STREAM)
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public byte[] postAndGetBinary(byte[] data) {
		return data;
	}

	public static byte[] toByteArray(InputStream is) {
		try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
			int reads = is.read();
			while (reads != -1) {
				baos.write(reads);
				reads = is.read();
			}
			return baos.toByteArray();
		} catch (Exception e) {
			LOG.error("failed to transform input stream into byte array", e);
			throw new RuntimeException(e);
		}
	}

	private InputStream loadRandomCatGif() {
		// retry since do not always get a picture
		for (int i = 0; i < NR_RETRIES; i++) {
			try {
				URL randomCatUrl = new URL("http://thecatapi.com/api/images/get?format=src&type=gif");
				HttpURLConnection httpConnection = (HttpURLConnection) randomCatUrl.openConnection();
				if (httpConnection.getResponseCode() == Status.OK.getStatusCode()) {
					return httpConnection.getInputStream();
				} else {
					String msg = "failed to load cat gif; status code: " + httpConnection.getResponseCode();
					LOG.error(msg);
					throw new RuntimeException(msg);
				}
			} catch (Exception e) {
				LOG.error("failed to load cat gif", e);
			}
		}
		throw new RuntimeException("failed to load cat gif");
	}
}
