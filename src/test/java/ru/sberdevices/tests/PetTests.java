package ru.sberdevices.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.sberdevices.PetController;
import ru.sberdevices.dto.AddPetResponseDTO;
import ru.sberdevices.factory.pet.PetDataFactory;

public class PetTests {


  /**
   * Тест проверяет создание нового животного + проверяет схему ответа
   * */
  @Test
  public void addNewPetTest(){
    PetController pc = new PetController();
    String name = "Fox";

    AddPetResponseDTO response = pc.addPet(PetDataFactory.createDefaultPetBody(name),
            "schema/AddPetResponseSchema.json");

    Assertions.assertAll(
            () -> Assertions.assertEquals(name,response.getName())
    );

  }
}
