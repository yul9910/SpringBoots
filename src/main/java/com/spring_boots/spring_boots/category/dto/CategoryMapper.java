package com.spring_boots.spring_boots.category.dto;

import com.spring_boots.spring_boots.category.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

public interface CategoryMapper {

  CategoryDetailDto categoryToCategoryDetailDto(Category category);

  void updateCategoryFromDto(CategoryRequestDto requestDto, @MappingTarget Category category);

  CategoryAdminItem categoryToCategoryAdminItem(Category category);

  @Mapping(target = "totalCount", expression = "java(categories.size())")
  @Mapping(target = "currentPage", constant = "0")
  @Mapping(target = "pageSize", expression = "java(categories.size())")
  CategoryAdminListDto toCategoryAdminListDtoWithoutPagination(List<Category> categories);

  @Mapping(target = "categories", source = "categories")
  @Mapping(target = "totalCount", source = "totalCount")
  @Mapping(target = "currentPage", source = "page")
  @Mapping(target = "pageSize", source = "size")
  CategoryAdminListDto toCategoryAdminListDto(List<Category> categories, long totalCount, int page, int size);

}
