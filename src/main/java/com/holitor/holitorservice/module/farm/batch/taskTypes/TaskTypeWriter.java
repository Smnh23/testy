package com.holitor.holitorservice.module.farm.batch.taskTypes;

import java.util.List;

import com.holitor.holitorservice.module.farm.model.TaskType;
import com.holitor.holitorservice.module.farm.repository.TaskTypeRepository;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

public class TaskTypeWriter implements ItemWriter<TaskType> {

  private @Autowired TaskTypeRepository taskTypeRepository;

	@Override
	public void write(List<? extends TaskType> taskTypes) throws Exception {
		taskTypes.stream().forEach(taskType -> taskTypeRepository.save(taskType));
	}

}