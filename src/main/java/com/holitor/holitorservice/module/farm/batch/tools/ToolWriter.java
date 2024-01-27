package com.holitor.holitorservice.module.farm.batch.tools;

import java.util.List;

import com.holitor.holitorservice.module.farm.model.Tool;
import com.holitor.holitorservice.module.farm.repository.ToolRepository;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

public class ToolWriter implements ItemWriter<Tool> {

  private @Autowired ToolRepository toolRepository;

	@Override
	public void write(List<? extends Tool> tools) throws Exception {
		tools.stream().forEach(tool -> toolRepository.save(tool));
	}

}