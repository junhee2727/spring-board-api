package com.junhee.spring_board_api.domain.post.entity;

import com.junhee.spring_board_api.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
//Table post {
//post_id     bigint       [pk, increment, note: '게시글 PK']
//member_id   bigint       [not null, ref: > member.member_id, note: '작성자 FK']
//category_id int          [not null, ref: > category.category_id, note: '카테고리 FK']
//title       varchar(255) [not null, note: '제목']
//content     text         [not null, note: '본문']
//view_count  int          [not null, default: 0, note: '조회수']
//status      varchar(10)  [not null, default: 'ACTIVE', note: 'ACTIVE | DELETED (소프트 딜리트)']
//created_at  datetime     [not null, default: `CURRENT_TIMESTAMP`, note: '작성일']
//updated_at  datetime     [not null, default: `CURRENT_TIMESTAMP`, note: '수정일']
//
//indexes {
//    member_id   [name: 'idx_post_member']
//    category_id [name: 'idx_post_category']
//    created_at  [name: 'idx_post_created']
//}
//}


@Getter
@NoArgsConstructor
@Entity
@Table(name = "post")
public class Post extends BaseEntity {
    public enum PostStatus {
        ACTIVE,
        DELETED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "member_id", nullable = false)
//    private Member member;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "category_id", nullable = false)
//    private Category category;
    @Column(length = 255, nullable = false)
    private String title;
    @Lob
    @Column(nullable = false)
    private String content;
    @Column(nullable = false)
    private int viewCount = 0;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private PostStatus status = PostStatus.ACTIVE;


    public Post(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
