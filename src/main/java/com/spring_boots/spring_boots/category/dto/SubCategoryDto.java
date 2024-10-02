package com.spring_boots.spring_boots.category.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class SubCategoryDto {
  private Long id;
  private String name;
  private String content;
  private String imageUrl;  // HOW TO 카테고리용
}
