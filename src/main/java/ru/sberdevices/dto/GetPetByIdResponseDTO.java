package ru.sberdevices.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetPetByIdResponseDTO {

  private int id;
  private Category category;
  private String name;
  private List<Tag> tags;
  private String status;

  @Data
  public static class Category {
    private int id;
    private String name;
  }

  @Data
  public static class Tag {
    private int id;
    private String name;
  }
}
