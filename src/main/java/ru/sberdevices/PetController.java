package ru.sberdevices;

import io.restassured.response.Response;
import ru.sberdevices.dto.AddPetDTO;
import ru.sberdevices.dto.AddPetResponseDTO;

import static ru.sberdevices.common.Config.PET_ENDPOINT;

public class PetController {
  private ApiClient apiClient;

  public PetController() {
    this.apiClient = new ApiClient.Builder().build();
  }

  public AddPetResponseDTO addPet(AddPetDTO body){
    ApiClient apiClient = new ApiClient.Builder().build();
    return apiClient.sendRequest("POST", PET_ENDPOINT, body)
            .as(AddPetResponseDTO.class);
  }

  public AddPetResponseDTO addPet(AddPetDTO body, String schemaPath) {
    ApiClient apiClient = new ApiClient.Builder().build();
    return apiClient
            .sendRequest("POST", PET_ENDPOINT, body, schemaPath)
            .as(AddPetResponseDTO.class);
  }
}
