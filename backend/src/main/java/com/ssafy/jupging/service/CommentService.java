package com.ssafy.jupging.service;

import com.ssafy.jupging.domain.entity.Comment;
import com.ssafy.jupging.domain.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;

    @Transactional
    public void saveComment(Comment comment) {
        commentRepository.save(comment);
    }

    @Transactional
    public void deleteComment(long commentId) {
        //특정 댓글 한 개 삭제
        Optional<Comment> comment = commentRepository.findByCommentId(commentId);
        commentRepository.delete(comment.get());
    }

    @Transactional
    public int countByArticleId(Long articleId) {
        return commentRepository.countByArticleId(articleId);
    }

    @Transactional
    public List<Comment> findAllComment(long articleId) {
        return commentRepository.findAllByArticleId(articleId);
    }

    @Transactional
    public void deleteAllComment(Long articleId) {
        //게시글 삭제할 때, 모든 댓글 삭제
        commentRepository.deleteAllByArticleId(articleId);
    }
}