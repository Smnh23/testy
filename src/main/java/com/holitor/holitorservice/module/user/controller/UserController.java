package com.holitor.holitorservice.module.user.controller;

import java.util.List;

import javax.validation.Valid;

import com.holitor.holitorservice.exception.ApiException;
import com.holitor.holitorservice.module.user.model.dto.UserDto;
import com.holitor.holitorservice.module.user.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping({"${url.api}"})
public class UserController {
  
  private @Autowired UserService userService;

  @PostMapping("/add-user")
  @ResponseStatus(HttpStatus.CREATED)
  public UserDto addUser(@RequestBody @Valid UserDto userDto) { return this.userService.addUser(userDto); }

  @GetMapping("/users")
  @ResponseStatus(HttpStatus.OK)
  public List<UserDto> getUsers() { return this.userService.getUsers(); }

  @GetMapping("/user/{idUser}")
  @ResponseStatus(HttpStatus.OK)
  public UserDto getUserById(@PathVariable long idUser) {
    UserDto userDto = null;
    try { userDto = this.userService.getUserById(idUser); }
    catch (ApiException exception) { throw new ResponseStatusException(exception.getCode(), exception.getMessage(), exception); }
    return userDto;
  }

  @GetMapping("/login/{pseudo}")
  @ResponseStatus(HttpStatus.OK)
  public UserDto getUserByPseudo(@PathVariable String pseudo) {
    UserDto userDto = null;
    try { userDto = this.userService.getUserByPseudo(pseudo); }
    catch (ApiException exception) { throw new ResponseStatusException(exception.getCode(), exception.getMessage(), exception); }
    return userDto;
  }

  @PutMapping("/user")
  @ResponseStatus(HttpStatus.OK)
  public UserDto updateUser(@Valid UserDto userDto, @RequestParam(name = "imageFile", required = false) MultipartFile imageFile) {
    UserDto updatedUserDto = null;
    try { updatedUserDto = this.userService.updateUser(userDto, imageFile); }
    catch (ApiException exception) { throw new ResponseStatusException(exception.getCode(), exception.getMessage(), exception); }
    return updatedUserDto;
  }

  @DeleteMapping("/user/{idUser}")
  @ResponseStatus(HttpStatus.OK)
  public List<UserDto> deleteUser(@PathVariable long idUser) {
    List<UserDto> users = null;
    try { users = this.userService.deleteUser(idUser); } 
    catch (ApiException exception) { throw new ResponseStatusException(exception.getCode(), exception.getMessage(), exception); }
    return users;
  }

}