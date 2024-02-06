package com.holitor.holitorservice.module.user.service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import com.holitor.holitorservice.exception.ApiException;
import com.holitor.holitorservice.module.image.model.Image;
import com.holitor.holitorservice.module.user.mapper.UserMapper;
import com.holitor.holitorservice.module.user.model.User;
import com.holitor.holitorservice.module.user.model.dto.UserDto;
import com.holitor.holitorservice.module.user.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UserService {

  private static final String USER_NOT_FOUND = "User %s not found !";
  private static final String FILE_ERROR = "File error.";

  private @Autowired UserRepository userRepository;
  private @Autowired UserMapper userMapper;

  @Transactional(readOnly = false)
  public UserDto addUser(UserDto userDto) { 
    User user = this.userMapper.toEntity(userDto);
    user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword())); 
    user.setImage(new Image());
    return this.userMapper.toDto(this.userRepository.save(user)); 
  }
  
  @Transactional(readOnly = true)
  public List<UserDto> getUsers() { 
    List<User> users = (List<User>) this.userRepository.findAll();
    return users.stream().map(user -> this.userMapper.toDto(user)).collect(Collectors.toList());
  }

  @Transactional(rollbackFor = ApiException.class, readOnly = true)
  public UserDto getUserById(long idUser) { 
    User user = this.userRepository.findById(idUser).orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "User "+idUser+" not found !"));
    return this.userMapper.toDto(user); 
  }

  @Transactional(rollbackFor = ApiException.class, readOnly = true)
  public UserDto getUserByPseudo(String pseudo) {
    User user = this.userRepository.findByPseudo(pseudo)
      .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "User "+pseudo+" not found !"));
    return this.userMapper.toDto(user); 
  }

  @Transactional(rollbackFor = ApiException.class, readOnly = false)
  public UserDto updateUser(UserDto userDto, @Nullable MultipartFile imageFile) {
    if (!this.userRepository.existsById(userDto.getId()))
      throw new ApiException(HttpStatus.NOT_FOUND, String.format(USER_NOT_FOUND, userDto.getId()));
    User user = this.userMapper.toEntity(userDto);
    Image image = this.userRepository.findById(userDto.getId()).get().getImage();
    if (imageFile != null) {
      try { user.setImage(new Image(image.getId(), imageFile.getOriginalFilename(), imageFile.getContentType(), imageFile.getBytes())); }
      catch (IOException exception) { throw new ApiException(HttpStatus.BAD_REQUEST, FILE_ERROR); }
    } else user.setImage(image);
    return this.userMapper.toDto(this.userRepository.save(user));
  }

  @Transactional(rollbackFor = ApiException.class, readOnly = false)
  public List<UserDto> deleteUser(long idUser) { 
    if (!this.userRepository.existsById(idUser)) 
      throw new ApiException(HttpStatus.NOT_FOUND, "User "+idUser+" not found !");
    this.userRepository.deleteById(idUser);
    return this.getUsers();
  }

}