package com.holitor.holitorservice.exception;

import org.springframework.http.HttpStatus;

public class ApiException extends RuntimeException {

  private HttpStatus code;

  public ApiException(HttpStatus code, String errorMessage) { 
    super(errorMessage); 
    this.code = code;
  }
  
  public HttpStatus getCode() { return code; }

  public void setCode(HttpStatus code) { this.code = code; }

}