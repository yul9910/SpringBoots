package com.spring_boots.spring_boots.category.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class CategoryDetailDto {
  private Long id;
  private String name;
  private String content;
  private Long parentId;
  private List<SubCategoryDto> subcategories;
}
