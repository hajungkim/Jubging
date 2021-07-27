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

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @CreationTimestamp
    @Column(nullable = false)
    private Timestamp createdDate;

    @Column(nullable = false)
    private Long articleId;

    @Column(updatable = false)
    private Long userId;

    @Column(length = 500 , nullable = false)
    private String commentContent;

    //@ManyToOne
    //@JoinColumn(name = "userId", insertable = false, updatable = false)
    //private User user;

    public static Comment saveComment(CommentSaveRequestDto request) {
        Comment comment = new Comment();
        comment.articleId=request.getArticleId();
        comment.userId=request.getUserId();
        comment.commentContent=request.getCommentContent();
        return comment;
    }
}
