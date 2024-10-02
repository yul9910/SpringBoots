package com.spring_boots.spring_boots.category.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CategoryRequestDto {

  @NotBlank(message = "카테고리 이름은 필수입니다.")
  private String categoryName;

  private String categoryContent;

  private Long parentId;

  private String imageUrl;

  private Integer displayOrder;


}
