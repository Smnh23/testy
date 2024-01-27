package com.holitor.holitorservice.module.blog.controller;

import java.util.List;

import javax.validation.Valid;

import com.holitor.holitorservice.module.blog.model.dto.PostDto;
import com.holitor.holitorservice.module.blog.service.PostService;
import com.holitor.holitorservice.exception.ApiException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("${url.api}")
public class PostController {

  private @Autowired PostService postService;

  @PostMapping("/post")
  @ResponseStatus(HttpStatus.CREATED)
  public PostDto addPost(@Valid PostDto postDto, @RequestParam(name = "imageFile", required = false) MultipartFile imageFile) {
    PostDto addedPostDto = null;
    try { addedPostDto = this.postService.addPost(postDto, imageFile); }
    catch (ApiException exception) { throw new ResponseStatusException(exception.getCode(), exception.getMessage(), exception); }
    return addedPostDto;
  }

  @GetMapping("/posts")
  @ResponseStatus(HttpStatus.OK)
  public List<PostDto> getPosts() { return this.postService.getPosts(); }

  @PutMapping("/post")
  @ResponseStatus(HttpStatus.OK)
  public PostDto updatePost(@Valid PostDto postDto, @RequestParam(name = "imageFile", required = false) MultipartFile imageFile) {
    PostDto updatedPostDto = null;
    try { updatedPostDto = this.postService.updatePost(postDto, imageFile); }
    catch (ApiException exception) { throw new ResponseStatusException(exception.getCode(), exception.getMessage(), exception); }
    return updatedPostDto;
  }

  @DeleteMapping("/post/{idPost}")
  @ResponseStatus(HttpStatus.OK)
  public List<PostDto> deletePost(@PathVariable long idPost) {
    List<PostDto> posts = null;
    try { posts = this.postService.deletePost(idPost); } 
    catch (ApiException exception) { throw new ResponseStatusException(exception.getCode(), exception.getMessage(), exception); }
    return posts;
  }

}