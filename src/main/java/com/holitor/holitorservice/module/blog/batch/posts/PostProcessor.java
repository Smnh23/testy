package com.holitor.holitorservice.module.blog.batch.posts;

import java.awt.Color;

import java.util.Date;
import java.util.Optional;
import java.util.Random;

import com.holitor.holitorservice.module.blog.model.Category;
import com.holitor.holitorservice.module.blog.model.Post;
import com.holitor.holitorservice.module.blog.repository.CategoryRepository;
import com.holitor.holitorservice.module.image.service.ImageService;
import com.holitor.holitorservice.module.user.repository.UserRepository;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.util.ResourceUtils;

public class PostProcessor implements ItemProcessor<PostData, Post> {

  private @Autowired CategoryRepository categoryRepository;
  private @Autowired UserRepository userRepository;

	@Override
	public Post process(@NonNull PostData postData) throws Exception {
    Post post = new Post();
    Optional<Category> category = categoryRepository.findByName(postData.getCategory());
    if (category.isPresent()) post.setCategory(category.get());
    else post.setCategory(createCategory(postData));
    post.setTitle(postData.getTitle());
    post.setDescription(postData.getDescription());
    post.setContent(postData.getContent());
    post.setImage(ImageService.readImage(ResourceUtils.getFile("classpath:"+postData.getImage())));
    post.setDate(new Date());
    post.setAuthor(userRepository.findAll().iterator().next());
		return post;
	}

  public Category createCategory(PostData postData) throws Exception {
    Category category = new Category();
    category.setColor(this.randomColor());
    category.setName(postData.getCategory());
    category.setDescription(postData.getCategoryDescription());
    category.setImage(ImageService.readImage(ResourceUtils.getFile("classpath:"+postData.getCategoryImage())));
    return categoryRepository.save(category);
  }

  public String randomColor() {
    Random rand = new Random();
    Color color = new Color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat(), rand.nextFloat());
    return String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
  }
  
}