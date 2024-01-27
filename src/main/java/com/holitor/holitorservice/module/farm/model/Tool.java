package com.holitor.holitorservice.module.farm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "`tool`")
@PrimaryKeyJoinColumn(name = "`farm_input_id`")
@EqualsAndHashCode(callSuper = false)
public class Tool extends FarmInput {

  public enum ToolType { TRACTOR, ROTOTILLER, HOE, HAND }

  @Enumerated(EnumType.STRING)
  @Column(name = "`tool_type`")
  private ToolType toolType;

}