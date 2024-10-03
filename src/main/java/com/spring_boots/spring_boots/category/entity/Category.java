package com.spring_boots.spring_boots.category.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "category")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "category_id")
  private Long id;

  @Column(name = "category_name")
  private String name;

  @Column(name = "category_content")
  private String content;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "parent_category_id")
  private Category parentCategory;

  @Column(name = "display_order")
  private Integer displayOrder;

  @Column(name = "image_url")
  private String imageUrl;


  // 1. 생성자를 통한 초기화
  // 2. 빌더 패턴 사용
  // 3. 비즈니스 메서드 구현


}
