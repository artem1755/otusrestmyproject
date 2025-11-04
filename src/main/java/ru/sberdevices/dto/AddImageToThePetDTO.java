package ru.sberdevices.dto;

import lombok.Data;

@Data
public class AddImageToThePetDTO {
  public int code;
  public String type;
  public String message;
}
