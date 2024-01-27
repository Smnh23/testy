package com.holitor.holitorservice.module.blog.repository;

import com.holitor.holitorservice.module.blog.model.Post;

import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long> { }