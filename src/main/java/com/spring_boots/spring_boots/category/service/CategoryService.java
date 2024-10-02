package com.spring_boots.spring_boots.category.service;

import com.spring_boots.spring_boots.category.config.error.BadRequestException;
import com.spring_boots.spring_boots.category.config.error.ResourceNotFoundException;
import com.spring_boots.spring_boots.category.dto.*;
import com.spring_boots.spring_boots.category.entity.Category;
import com.spring_boots.spring_boots.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {

  private final CategoryRepository categoryRepository;
  private final CategoryMapper categoryMapper;


  // 새 카테고리 생성
  @Transactional
  public CategoryDetailDto createCategory(CategoryRequestDto requestDto) {
    Category category = new Category();
    categoryMapper.updateCategoryFromDto(requestDto, category);
    Category savedCategory = categoryRepository.save(category);
    return categoryMapper.categoryToCategoryDetailDto(savedCategory);
  }


  // 카테고리 수정
  @Transactional
  public CategoryDetailDto updateCategory(Long categoryId, CategoryRequestDto requestDto) {
    Category category = categoryRepository.findById(categoryId)
        .orElseThrow(() -> new ResourceNotFoundException("카테고리를 찾을 수 없습니다: " + categoryId));
    categoryMapper.updateCategoryFromDto(requestDto, category);
    Category updatedCategory = categoryRepository.save(category);
    return categoryMapper.categoryToCategoryDetailDto(updatedCategory);
  }


  // 카테고리 삭제
  @Transactional
  public void deleteCategory(Long categoryId) {
    Category category = categoryRepository.findById(categoryId)
        .orElseThrow(() -> new ResourceNotFoundException("삭제할 카테고리를 찾을 수 없습니다: " + categoryId));
    categoryRepository.delete(category);
  }


  // 전체 카테고리 목록 조회
  public CategoryAdminListDto getAllCategories() {
    List<Category> categories = categoryRepository.findAll();
    return categoryMapper.toCategoryAdminListDtoWithoutPagination(categories);
  }


  // 카테고리 상세 조회
  public CategoryDetailDto getCategoryDetail(Long categoryId) {
    Category category = categoryRepository.findById(categoryId)
        .orElseThrow(() -> new ResourceNotFoundException("카테고리를 찾을 수 없습니다: " + categoryId));
    return categoryMapper.categoryToCategoryDetailDto(category);
  }


  // 관리자용 카테고리 목록 페이지네이션 적용하여 조회
  public CategoryAdminListDto getAdminCategories(int page, int limit) {
    PageRequest pageRequest = PageRequest.of(page, limit);
    Page<Category> categoryPage = categoryRepository.findAll(pageRequest);
    return categoryMapper.toCategoryAdminListDto(
        categoryPage.getContent(),
        categoryPage.getTotalElements(),
        page,
        limit
    );
  }


}
