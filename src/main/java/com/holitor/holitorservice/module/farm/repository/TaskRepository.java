package com.holitor.holitorservice.module.farm.repository;

import com.holitor.holitorservice.module.farm.model.Task;

import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository<Task, Long> { }