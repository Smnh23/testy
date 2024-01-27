package com.holitor.holitorservice.module.farm.model.dto;

import com.holitor.holitorservice.module.farm.model.Tool.ToolType;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ToolDto extends FarmInputDto {

  private ToolType toolType;

}