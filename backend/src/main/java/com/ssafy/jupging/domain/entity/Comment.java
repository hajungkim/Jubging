package com.ssafy.jupging.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ssafy.jupging.dto.CommentSaveRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.ManyToAny;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    private Date createdDate;

    @Column(nullable = false)
    private Long articleId;

    @Column(updatable = false)
    private Long userId;

    @Column(length = 500 , nullable = false)
    private String commentContent;

    public static Comment saveComment(CommentSaveRequestDto request) {
        Comment comment = new Comment();
        comment.articleId=request.getArticleId();
        comment.userId=request.getUserId();
        comment.commentContent=request.getCommentContent();
        comment.createdDate= new Date();
        return comment;
    }
}
