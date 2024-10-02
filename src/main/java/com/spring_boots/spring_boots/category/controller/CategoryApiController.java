package com.spring_boots.spring_boots.category.controller;

import com.spring_boots.spring_boots.category.dto.*;
import com.spring_boots.spring_boots.category.service.CategoryService;
import com.spring_boots.spring_boots.category.config.error.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CategoryApiController {

  private final CategoryService categoryService;

  //관리자 - 새 카테고리 추가
  @PostMapping("/admin/categories")
  public ResponseEntity<CategoryDetailDto> createCategory(@RequestBody CategoryRequestDto requestDto) {
    try {
      CategoryDetailDto responseDto = categoryService.createCategory(requestDto);
      return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    } catch (BadRequestException e) {
      throw new BadRequestException("필수_파라미터_누락", "카테고리 생성 실패: " + e.getMessage());
    }
  }

  // 관리자 - 카테고리 정보 수정
  @PutMapping("/admin/categories/{category_id}")
  public ResponseEntity<CategoryDetailDto> updateCategory(
      @PathVariable("category_id") Long categoryId,
      @RequestBody CategoryRequestDto requestDto) {
    try {
      CategoryDetailDto responseDto = categoryService.updateCategory(categoryId, requestDto);
      return ResponseEntity.ok(responseDto);
    } catch (ResourceNotFoundException e) {
      throw new ResourceNotFoundException("카테고리를 찾을 수 없습니다: " + categoryId);
    } catch (BadRequestException e) {
      throw new BadRequestException("파라미터_길이_초과", "카테고리 수정 실패: " + e.getMessage());
    }
  }

  // 관리자 - 카테고리 삭제
  @DeleteMapping("/admin/categories/{category_id}")
  public ResponseEntity<Void> deleteCategory(@PathVariable("category_id") Long categoryId) {
    try {
      categoryService.deleteCategory(categoryId);
      return ResponseEntity.noContent().build();
    } catch (ResourceNotFoundException e) {
      throw new ResourceNotFoundException("삭제할 카테고리를 찾을 수 없습니다: " + categoryId);
    }
  }

  // 카테고리 목록 조회 (페이지네이션 없음)
  @GetMapping("/categories")
  public ResponseEntity<CategoryAdminListDto> getAllCategories() {
    CategoryAdminListDto categories = categoryService.getAllCategories();
    return ResponseEntity.ok(categories);
  }

  // 카테고리 상세 조회
  @GetMapping("/categories/{category_id}")
  public ResponseEntity<CategoryDetailDto> getCategoryDetail(@PathVariable("category_id") Long categoryId) {
    try {
      CategoryDetailDto detailDto = categoryService.getCategoryDetail(categoryId);
      return ResponseEntity.ok(detailDto);
    } catch (ResourceNotFoundException e) {
      throw new ResourceNotFoundException("카테고리를 찾을 수 없습니다: " + categoryId);
    }
  }

  // 관리자 카테고리 전체 목록 조회 (페이지네이션)
  @GetMapping("/admin/categories")
  public ResponseEntity<CategoryAdminListDto> getAdminCategories(
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int limit) {
    try {
      CategoryAdminListDto listDto = categoryService.getAdminCategories(page, limit);
      return ResponseEntity.ok(listDto);
    } catch (BadRequestException e) {
      throw new BadRequestException("잘못된_파라미터_형식", "잘못된 페이지네이션 파라미터: " + e.getMessage());
    }
  }
}
