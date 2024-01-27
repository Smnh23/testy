package com.holitor.holitorservice.module.farm.batch.tools;

import com.holitor.holitorservice.module.farm.model.Tool;
import com.holitor.holitorservice.module.farm.model.FarmInput.FarmInputType;
import com.holitor.holitorservice.module.farm.model.Tool.ToolType;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.lang.NonNull;

public class ToolProcessor implements ItemProcessor<ToolData, Tool> {

	@Override
	public Tool process(@NonNull ToolData toolData) throws Exception {
    Tool tool = new Tool();
    tool.setName(toolData.getName());
    tool.setBrand(toolData.getBrand());
    tool.setPrice(Double.parseDouble(toolData.getPrice()));
    tool.setToolType(ToolType.valueOf(toolData.getToolType()));
    tool.setType(FarmInputType.TOOL);
    return tool;
	}

}