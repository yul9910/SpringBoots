package com.spring_boots.spring_boots.category.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CategoryAdminItem {
  private Long id;
  private String name;
  private Long parentId;
  private String parentName;  // 카테고리 테마 (대분류 카테고리)
  private Integer displayOrder;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
}
