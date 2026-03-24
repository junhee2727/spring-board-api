package com.junhee.spring_board_api.domain.comment.entity;

import com.junhee.spring_board_api.domain.common.BaseEntity;
import com.junhee.spring_board_api.domain.member.entity.Member;
import com.junhee.spring_board_api.domain.post.entity.Post;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
//table comment {
//comment_id  bigint   [pk, increment, note: '댓글 PK']
//post_id     bigint   [not null, ref: > post.post_id, note: '게시글 FK']
//member_id   bigint   [not null, ref: > member.member_id, note: '작성자 FK']
//parent_id   bigint   [null, ref: > comment.comment_id, note: '부모 댓글 FK (NULL=댓글, 값 있음=대댓글)']
//content     text     [not null, note: '댓글 내용']
//created_at  datetime [not null, default: `CURRENT_TIMESTAMP`, note: '작성일']
//updated_at  datetime [not null, default: `CURRENT_TIMESTAMP`, note: '수정일']
//
//indexes {
//    post_id   [name: 'idx_comment_post']
//    member_id [name: 'idx_comment_member']
//    parent_id [name: 'idx_comment_parent']
//}
//}

@Entity
@Getter
@NoArgsConstructor
@Table(name = "comment", indexes = {
        @Index(name = "idx_comment_post", columnList = "post_id"),
        @Index(name = "idx_comment_member", columnList = "member_id"),
        @Index(name = "idx_comment_parent", columnList = "parent_id")
})
public class Comment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;
    //게시글 (N:1)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    //작성자 (N:1)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Comment parent = null;

    @Lob
    @Column(nullable = false)
    private String content;
}
