package ru.sberdevices.factory.pet;

import ru.sberdevices.dto.AddPetDTO;

import java.util.List;

public class PetDataFactory {

  public static AddPetDTO createDefaultPetBody(String name) {
    AddPetDTO.Category category = AddPetDTO.Category.builder()
            .id(12)
            .name("wild")
            .build();

    AddPetDTO.Tag tag = AddPetDTO.Tag.builder()
            .id(0)
            .name("string")
            .build();

    return AddPetDTO.builder()
            .id(1)
            .name(name)
            .category(category)
            .tags(List.of(tag))
            .status("available")
            .build();
  }
}
