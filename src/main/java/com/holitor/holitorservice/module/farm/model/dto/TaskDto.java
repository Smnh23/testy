package com.holitor.holitorservice.module.farm.model.dto;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import lombok.Data;

@Data
public class TaskDto {
  
  private @NotNull long id;
  private @NotNull long idType;
  private Date predictedDate;
  private @Null Date actualDate;
  private long duration;
  private List<Long> idsTools;

}