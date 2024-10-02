package com.spring_boots.spring_boots.category.dto;

import com.spring_boots.spring_boots.category.entity.Category;
import com.spring_boots.spring_boots.category.repository.CategoryRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class CategoryMapper {

  @Autowired
  protected CategoryRepository categoryRepository;

  // Category 엔티티를 CategoryDetailDto로 변환
  @Mapping(target = "name", source = "name")
  @Mapping(target = "content", source = "description")
  @Mapping(target = "parentId", source = "parentId")
  @Mapping(target = "subcategories", ignore = true)  // 하위 카테고리 매핑은 별도 로직 필요
  public abstract CategoryDetailDto categoryToCategoryDetailDto(Category category);

  // CategoryRequestDto를 이용해 Category 엔티티 업데이트
  @Mapping(target = "id", ignore = true)
  public abstract void updateCategoryFromDto(CategoryRequestDto requestDto, @MappingTarget Category category);

  // Category 엔티티를 CategoryAdminItem으로 변환
  @Mapping(target = "name", source = "name")
  @Mapping(target = "parentId", source = "parentId")
  @Mapping(target = "parentName", expression = "java(getParentName(category))")
  public abstract CategoryAdminItem categoryToCategoryAdminItem(Category category);

  // Category 리스트를 CategoryAdminListDto로 변환 (페이지네이션 포함)
  @Mapping(target = "categories", source = "categories")
  @Mapping(target = "totalCount", source = "totalCount")
  @Mapping(target = "currentPage", source = "page")
  @Mapping(target = "pageSize", source = "size")
  public abstract CategoryAdminListDto toCategoryAdminListDto(List<Category> categories, long totalCount, int page, int size);

  // Category 리스트를 CategoryAdminListDto로 변환 (페이지네이션 없음)
  @Mapping(target = "categories", expression = "java(categoriesToCategoryAdminItems(categories))")
  @Mapping(target = "totalCount", expression = "java(categories.size())")
  @Mapping(target = "currentPage", constant = "0")
  @Mapping(target = "pageSize", expression = "java(categories.size())")
  public abstract CategoryAdminListDto toCategoryAdminListDtoWithoutPagination(List<Category> categories);

  // Category 리스트를 CategoryAdminItem 리스트로 변환
  public abstract List<CategoryAdminItem> categoriesToCategoryAdminItems(List<Category> categories);

  // 부모 카테고리 이름 가져오기
  protected String getParentName(Category category) {
    if (category.getParentId() != null) {
      return categoryRepository.findById(category.getParentId())
          .map(Category::getName)
          .orElse(null);
    }
    return null;
  }
}


