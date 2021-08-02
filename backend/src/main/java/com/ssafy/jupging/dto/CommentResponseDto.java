package com.ssafy.jupging.dto;

import com.ssafy.jupging.domain.entity.Comment;
import com.ssafy.jupging.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponseDto {
    //유저 정보
    private String profilePath;
    private Long userId;
    private String nickname;

    //댓글 정보
    private Long commentId;
    private String commentContent;

    public CommentResponseDto(Comment comment, User user){
        this.profilePath=user.getProfilePath();
        this.userId=user.getUserId();
        this.nickname=user.getNickname();

        this.commentId=comment.getCommentId();
        this.commentContent=comment.getCommentContent();
    }
}
