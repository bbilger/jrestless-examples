package com.jrestless.aws.examples;

import com.jrestless.aws.examples.SampleResource.PojoDto;

import feign.Headers;
import feign.Param;
import feign.RequestLine;

@Headers({
	"Accept: application/json",
	"Content-Type: application/json"
})
public interface BackendService {
	@RequestLine("GET /api/info")
	PojoDto getInfo();
	@RequestLine("GET /api/pathparam/{value}")
	PojoDto getPathParam(@Param("value") String value);
	@RequestLine("GET /api/queryparam?value={value}")
	PojoDto getQueryParam(@Param("value") String value);
	@RequestLine("GET /api/service-request")
	PojoDto getServiceRequest();
	@RequestLine("GET /api/lambda-context")
	PojoDto getLambdaContext();
	@RequestLine("POST /api/post")
	PojoDto post(PojoDto data);
}
