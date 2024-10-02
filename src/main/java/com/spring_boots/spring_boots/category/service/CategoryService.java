package com.spring_boots.spring_boots.category.service;

import com.spring_boots.spring_boots.category.config.error.BadRequestException;
import com.spring_boots.spring_boots.category.config.error.ResourceNotFoundException;
import com.spring_boots.spring_boots.category.dto.CategoryAdminItem;
import com.spring_boots.spring_boots.category.dto.CategoryAdminListDto;
import com.spring_boots.spring_boots.category.dto.CategoryDetailDto;
import com.spring_boots.spring_boots.category.dto.CategoryRequestDto;
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

  // 새 카테고리 생성
  @Transactional
  public CategoryDetailDto createCategory(CategoryRequestDto requestDto) {
    Category category = new Category();
    updateCategoryFromDto(category, requestDto);
    Category savedCategory = categoryRepository.save(category);
    return convertToCategoryDetailDto(savedCategory);
  }

  // 카테고리 수정
  @Transactional
  public CategoryDetailDto updateCategory(Long categoryId, CategoryRequestDto requestDto) {
    Category category = categoryRepository.findById(categoryId)
        .orElseThrow(() -> new ResourceNotFoundException("카테고리를 찾을 수 없습니다: " + categoryId));
    updateCategoryFromDto(category, requestDto);
    Category updatedCategory = categoryRepository.save(category);
    return convertToCategoryDetailDto(updatedCategory);
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
    return convertToCategoryAdminListDto(categories, categories.size(), 0, categories.size());
  }

  // 카테고리 상세 조회
  public CategoryDetailDto getCategoryDetail(Long categoryId) {
    Category category = categoryRepository.findById(categoryId)
        .orElseThrow(() -> new ResourceNotFoundException("카테고리를 찾을 수 없습니다: " + categoryId));
    return convertToCategoryDetailDto(category);
  }

  // 관리자용 카테고리 목록 페이지네이션 적용하여 조회
  public CategoryAdminListDto getAdminCategories(int page, int limit) {
    PageRequest pageRequest = PageRequest.of(page, limit);
    Page<Category> categoryPage = categoryRepository.findAll(pageRequest);
    return convertToCategoryAdminListDto(
        categoryPage.getContent(),
        categoryPage.getTotalElements(),
        page,
        limit
    );
  }





  // mapper 구현으로 아래 내용 단순화 작업 필요

  /**
   * DTO의 정보로 카테고리 엔티티를 업데이트합니다.
   * @param category 업데이트할 카테고리 엔티티
   * @param requestDto 카테고리 요청 정보
   */
  private void updateCategoryFromDto(Category category, CategoryRequestDto requestDto) {
    category.setName(requestDto.getName());
    category.setDescription(requestDto.getDescription());
    category.setParentId(requestDto.getParentId());
    category.setImageUrl(requestDto.getImageUrl());
    category.setDisplayOrder(requestDto.getDisplayOrder());
  }

  /**
   * 카테고리 엔티티를 CategoryDetailDto로 변환합니다.
   * @param category 변환할 카테고리 엔티티
   * @return 변환된 CategoryDetailDto
   */
  private CategoryDetailDto convertToCategoryDetailDto(Category category) {
    CategoryDetailDto dto = new CategoryDetailDto();
    dto.setId(category.getId());
    dto.setName(category.getName());
    dto.setDescription(category.getDescription());
    dto.setParentId(category.getParentId());
    dto.setImageUrl(category.getImageUrl());
    // 하위 카테고리 설정 로직
    return dto;
  }

  /**
   * 카테고리 리스트를 CategoryAdminListDto로 변환합니다.
   * @param categories 변환할 카테고리 리스트
   * @param totalCount 전체 카테고리 수
   * @param page 현재 페이지 번호
   * @param size 페이지 크기
   * @return 변환된 CategoryAdminListDto
   */
  private CategoryAdminListDto convertToCategoryAdminListDto(List<Category> categories, long totalCount, int page, int size) {
    List<CategoryAdminItem> items = categories.stream()
        .map(this::convertToCategoryAdminItem)
        .collect(Collectors.toList());

    CategoryAdminListDto dto = new CategoryAdminListDto();
    dto.setCategories(items);
    dto.setTotalCount(totalCount);
    dto.setCurrentPage(page);
    dto.setPageSize(size);
    return dto;
  }

  /**
   * 카테고리 엔티티를 CategoryAdminItem으로 변환합니다.
   * @param category 변환할 카테고리 엔티티
   * @return 변환된 CategoryAdminItem
   */
  private CategoryAdminItem convertToCategoryAdminItem(Category category) {
    CategoryAdminItem item = new CategoryAdminItem();
    item.setId(category.getId());
    item.setName(category.getName());
    item.setParentId(category.getParentId());
    item.setDisplayOrder(category.getDisplayOrder());
    item.setCreatedAt(category.getCreatedAt());
    item.setUpdatedAt(category.getUpdatedAt());
    // 부모 카테고리 이름 설정 로직
    return item;
  }
}
