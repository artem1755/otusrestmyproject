package ru.sberdevices.tests;

import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.sberdevices.ApiAssertions;
import ru.sberdevices.PetController;
import ru.sberdevices.dto.AddImageToThePetDTO;
import ru.sberdevices.dto.AddPetResponseDTO;
import ru.sberdevices.factory.pet.PetDataFactory;
import java.io.File;

public class PetTests {

  /**
   * Тест проверяет создание нового животного:
   *  Статус-код, параметр из тела ответа и схему
   * */
  @Test
  public void addNewPetTest(){
    PetController pc = new PetController();
    String name = "Fox";

    Response response = pc.addPet(PetDataFactory.createDefaultPetBody(name));

    ApiAssertions.assertThat(response)
            .hasStatusCode(200)
            .matchesSchema("schema/AddPetResponseSchema.json");

    AddPetResponseDTO responseBody = response.as(AddPetResponseDTO.class);

    Assertions.assertAll(
            () -> Assertions.assertEquals(name,responseBody.getName())
    );
  }


  /**
   * Тест проверяет загрузку изображения чущности pet:
   *  Статус-код, поле type
   * */
  @Test
  public void addImageToThePetTest(){
    PetController pc = new PetController();

    String metadata = "my fox image";
    File file = new File("src/main/resources/img/cat.jpg");
    String name = "Fox";

    long petId = pc.addPet(PetDataFactory.createDefaultPetBody(name))
            .as(AddPetResponseDTO.class)
            .getId();

    Response response = pc.uploadImage(petId, metadata, file);

    AddImageToThePetDTO responseBody = response.as(AddImageToThePetDTO.class);

    ApiAssertions.assertThat(response)
            .hasStatusCode(200);

    Assertions.assertAll(
            () -> Assertions.assertEquals("unknown",responseBody.getType())
    );
  }

}
