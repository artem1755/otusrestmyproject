package ru.sberdevices.dto;

import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class AddPetDTO {
  private int id;
  private Category category;
  private String name;
  private List<String> photoUrls;
  private List<Tag> tags;
  private String status;

  @Data
  @Builder
  public static class Category {
    private int id;
    private String name;
  }

  @Data
  @Builder
  public static class Tag {
    private int id;
    private String name;
  }
}
