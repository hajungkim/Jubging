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
        Optional<Comment> comment = commentRepository.findByCommentId(commentId);
        commentRepository.delete(comment.get());
    }

    @Transactional
    public int countByArticleId(Long articleId) {
        return commentRepository.countByArticleId(articleId);
    }


    public List<Comment> findAllComment(long articleId) {
        return commentRepository.findAllByArticleId(articleId);
    }
}