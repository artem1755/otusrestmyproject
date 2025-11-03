package ru.sberdevices;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import ru.sberdevices.utills.PropertyLoader;
import java.util.Map;

public class ApiClient {
    private RequestSpecification requestSpec;

    private ApiClient(Builder builder) {

        this.requestSpec = builder.requestSpecBuilder.build();
    }

    public static class Builder {
        private RequestSpecBuilder requestSpecBuilder;

        public Builder() {
            requestSpecBuilder = new RequestSpecBuilder()
                    .setBaseUri(PropertyLoader.getBaseUrl());
        }

        public Builder addHeader(String name, String value) {
            requestSpecBuilder.addHeader(name, value);
            return this;
        }

        public Builder addQueryParam(String name, String value) {
            requestSpecBuilder.addQueryParam(name, value);
            return this;
        }

        public Builder addQueryParam(Map<String, Object> params) {
            if (params != null) {
                for (Map.Entry<String, Object> entry : params.entrySet()) {
                    requestSpecBuilder.addQueryParam(entry.getKey(), entry.getValue());
                }
            }
            return this;
        }

        public Builder addHeaders(Map<String, Object> headers) {
            if (headers != null) {
                for (Map.Entry<String, Object> entry : headers.entrySet()) {
                    requestSpecBuilder.addHeader(entry.getKey(), entry.getValue().toString());
                }
            }
            return this;
        }

        public Builder addPathParam(String name, String value) {
            requestSpecBuilder.addPathParam(name, value);
            return this;
        }

        public Builder addCookies(Map<String, Object> cookies) {
            if (cookies != null) {
                for (Map.Entry<String, Object> entry : cookies.entrySet()) {
                    requestSpecBuilder.addCookie(entry.getKey(), entry.getValue().toString());
                }
            }
            return this;
        }

        public ApiClient build() {
            return new ApiClient(this);
        }
    }

    public Response sendRequest(String method, String endpoint, Object body) {
        RequestSpecification spec = RestAssured.given().spec(requestSpec).log().all();

        if (body != null) {
            spec.body(body).contentType(ContentType.JSON);
        }

        Response response = spec.request(method, endpoint);
        response.then().log().all();

        return response;
    }

    public Response sendRequest(String method, String endpoint, Object body, String schemaPath) {
        RequestSpecification spec = RestAssured.given().spec(requestSpec).log().all();

        if (body != null) {
            spec.body(body).contentType(ContentType.JSON);
        }

        Response response = spec.request(method, endpoint);
        response.then().log().all();

        if (schemaPath != null) {
            response.then()
                    .assertThat()
                    .body(JsonSchemaValidator.matchesJsonSchemaInClasspath(schemaPath));
        }

        return response;
    }
}
