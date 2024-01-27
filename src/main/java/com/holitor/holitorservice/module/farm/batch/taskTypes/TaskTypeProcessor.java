package com.holitor.holitorservice.module.farm.batch.taskTypes;

import com.holitor.holitorservice.module.farm.model.TaskType;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.lang.NonNull;

public class TaskTypeProcessor implements ItemProcessor<TaskTypeData, TaskType> {

	@Override
	public TaskType process(@NonNull TaskTypeData taskTypeData) throws Exception {
    TaskType taskType = new TaskType();
    taskType.setName(taskTypeData.getName());
    return taskType;
	}

}