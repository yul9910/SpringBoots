package com.spring_boots.spring_boots.category.config.error;

// 404 Not Found Error
public class ResourceNotFoundException extends RuntimeException {
  public ResourceNotFoundException(String message) {
    super(message);
  }
}
