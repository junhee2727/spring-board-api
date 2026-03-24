package com.junhee.spring_board_api.domain.category.entity;
//Table category {
//category_id int          [pk, increment, note: '카테고리 PK']
//name        varchar(50)  [not null, unique, note: '카테고리명']
//sort_order  int          [not null, default: 0, note: '정렬 순서']
//created_at  datetime     [not null, default: `CURRENT_TIMESTAMP`, note: '생성일']
//        }

import com.junhee.spring_board_api.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "category")
public class Category extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int categoryId;
    @Column(length = 50, nullable = false, unique = true)
    private String name;
    @Column(nullable = false)
    private int sortOrder = 0;
}
