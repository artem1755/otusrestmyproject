package ru.sberdevices;

import io.restassured.response.Response;
import org.assertj.core.api.Assertions;

public class ApiAssertions {
  private final Response response;

  private ApiAssertions(Response response) {
    this.response = response;
  }

  public static ApiAssertions assertThat(Response response) {
    return new ApiAssertions(response);
  }

  public ApiAssertions hasStatusCode(int expectedStatusCode) {
    int actualStatusCode = response.getStatusCode();
    Assertions.assertThat(actualStatusCode)
            .as("Проверка HTTP статуса")
            .isEqualTo(expectedStatusCode);
    return this;
  }

  public ApiAssertions matchesSchema(String schemaPath) {
    response.then().body(io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath(schemaPath));
    return this;
  }

  public ApiAssertions hasFieldEqualTo(String jsonPath, Object expectedValue) {
    Object actualValue = response.jsonPath().get(jsonPath);
    Assertions.assertThat(actualValue)
            .as("Проверка поля '" + jsonPath + "'")
            .isEqualTo(expectedValue);
    return this;
  }
}
