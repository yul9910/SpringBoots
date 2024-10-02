package com.spring_boots.spring_boots.category.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CategoryRequestDto {
  private String name;
  private String content;
  private Long parentId;
  private String imageUrl;
  private Integer displayOrder;


}
