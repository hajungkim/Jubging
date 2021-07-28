package com.ssafy.jupging.dto;

public interface CommentMapping {

    Long getCommentId();
    String getCommentContent();
    Long getUserId();

    String getUserNickname();
    String getUserProfilePath();
}
