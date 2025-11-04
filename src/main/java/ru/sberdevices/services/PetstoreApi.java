package ru.sberdevices.services;

import static io.restassured.RestAssured.given;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import ru.sberdevices.dto.AddPetDTO;
import ru.sberdevices.utills.PropertyLoader;

public class PetstoreApi {
  private RequestSpecification spec;
  private static final String BASE_URL = PropertyLoader.getBaseUrl();
  public static final String USER = "/pet";

  public PetstoreApi(){
    spec = given()
            .baseUri(BASE_URL)
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
