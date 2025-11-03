package ru.sberdevices.services;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import ru.sberdevices.dto.AddPetDTO;
import ru.sberdevices.utills.PropertyLoader;

import static io.restassured.RestAssured.given;

public class PetstoreApi {
  private RequestSpecification spec;
  private static final String baseUri = PropertyLoader.getBaseUrl();
  public static final String USER = "/pet";

  public PetstoreApi(){
    spec = given()
            .baseUri(baseUri)
            .contentType(ContentType.JSON)
            .log().all();
  }

  public ValidatableResponse createPet(AddPetDTO petDTO){
    return given(spec)
            .body(petDTO)
            .when()
            .post(USER)
            .then()
            .log().all();
  }
}
