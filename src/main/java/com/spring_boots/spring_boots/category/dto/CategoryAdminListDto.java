package com.spring_boots.spring_boots.category.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
public class CategoryAdminListDto {
  private List<CategoryAdminItem> categories;
  private long totalCount;
  private int currentPage;
  private int pageSize;
}


