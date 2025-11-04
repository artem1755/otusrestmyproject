package ru.sberdevices.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddPetResponseDTO {
  private int id;
  private Category category;
  private String name;
  private List<Tag> tags;
  private String status;

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  public static class Category {
    private int id;
    private String name;
  }

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  public static class Tag {
    private int id;
    private String name;
  }
}
