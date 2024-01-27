package com.holitor.holitorservice.module.blog.service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import com.holitor.holitorservice.module.blog.mapper.PostMapper;
import com.holitor.holitorservice.module.blog.model.Post;
import com.holitor.holitorservice.module.blog.model.dto.PostDto;
import com.holitor.holitorservice.module.blog.repository.PostRepository;
import com.holitor.holitorservice.exception.ApiException;
import com.holitor.holitorservice.module.image.model.Image;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PostService {

  private static final String POST_NOT_FOUND = "Post %s not found !";
  private static final String FILE_ERROR = "File error.";

  private @Autowired PostRepository postRepository;
  private @Autowired PostMapper postMapper;

  @Transactional(rollbackFor = ApiException.class, readOnly = false)
  public PostDto addPost(PostDto postDto, @Nullable MultipartFile imageFile) {
    Post post = this.postMapper.toEntity(postDto);
    if (imageFile != null) {
      try { post.setImage(new Image(imageFile.getOriginalFilename(), imageFile.getContentType(), imageFile.getBytes())); }
      catch (IOException exception) { throw new ApiException(HttpStatus.BAD_REQUEST, FILE_ERROR); }
    } else post.setImage(new Image());
    return this.postMapper.toDto(this.postRepository.save(post));
  }

  @Transactional(readOnly = true)
  public List<PostDto> getPosts() { 
    List<Post> posts = (List<Post>) this.postRepository.findAll();
    return posts.stream().map(post -> this.postMapper.toDto(post)).collect(Collectors.toList());
  }

  @Transactional(rollbackFor = ApiException.class, readOnly = false)
  public PostDto updatePost(PostDto postDto, @Nullable MultipartFile imageFile) {
    if (!this.postRepository.existsById(postDto.getId()))
      throw new ApiException(HttpStatus.NOT_FOUND, String.format(POST_NOT_FOUND, postDto.getId()));
    Post post = this.postMapper.toEntity(postDto);
    Image image = this.postRepository.findById(postDto.getId()).get().getImage();
    if (imageFile != null) {
      try { post.setImage(new Image(image.getId(), imageFile.getOriginalFilename(), imageFile.getContentType(), imageFile.getBytes())); }
      catch (IOException exception) { throw new ApiException(HttpStatus.BAD_REQUEST, FILE_ERROR); }
    } else post.setImage(image);
    return this.postMapper.toDto(this.postRepository.save(post)); 
  }

  @Transactional(rollbackFor = ApiException.class, readOnly = false)
  public List<PostDto> deletePost(long idPost) { 
    if (!this.postRepository.existsById(idPost)) 
      throw new ApiException(HttpStatus.NOT_FOUND, String.format(POST_NOT_FOUND, idPost));
    this.postRepository.deleteById(idPost);
    return this.getPosts();
  }

}