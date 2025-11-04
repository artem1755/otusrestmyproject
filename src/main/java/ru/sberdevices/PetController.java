package ru.sberdevices;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import ru.sberdevices.dto.AddPetDTO;
import ru.sberdevices.dto.AddPetResponseDTO;

import java.io.File;

import static ru.sberdevices.common.Config.PET_ENDPOINT;
import static ru.sberdevices.common.Config.UPLOADIMAGE_ENDPOINT;

public class PetController {
  private ApiClient apiClient;

  public PetController() {
    this.apiClient = new ApiClient.Builder().build();
  }

  public Response addPet(AddPetDTO body){
    ApiClient apiClient = new ApiClient.Builder().build();
    return apiClient.sendRequest("POST", PET_ENDPOINT, body);
  }

  public Response uploadImage(long petId, String additionalMetadata, File file) {
    ApiClient apiClient = new ApiClient.Builder()
            .addPathParam("petId", String.valueOf(petId))
            .build();

    return RestAssured
            .given()
            .spec(apiClient.getRequestSpec())
            .multiPart("additionalMetadata", additionalMetadata)
            .multiPart("file", file)
            .log().all()
            .when()
            .post(UPLOADIMAGE_ENDPOINT)
            .then()
            .log().all()
            .extract().response();
  }
}
