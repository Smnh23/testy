package com.holitor.holitorservice.module.blog.batch.posts;

import java.util.List;

import com.holitor.holitorservice.module.blog.model.Post;
import com.holitor.holitorservice.module.blog.repository.PostRepository;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

public class PostWriter implements ItemWriter<Post> {

  private @Autowired PostRepository postRepository;

	@Override
	public void write(List<? extends Post> posts) throws Exception {
		posts.stream().forEach(post -> postRepository.save(post));
	}

}