package com.krimo.daevitserver.service;

import com.krimo.daevitserver.model.Comment;
import com.krimo.daevitserver.repository.CommentRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CommentService {

    Comment saveComment(Comment comment);
    void deleteComment(Long id);
    Comment getComment(Long id);
    List<Comment> getBaseComments(Long postId, int offset, int count);
    List<Comment> getChildComments(Long parentId, int offset, int count);

}

@Service
class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public Comment getComment(Long id) {
        return commentRepository.findById(id).orElse(null);
    }

    @Override
    public Comment saveComment(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }

    @Override
    public List<Comment> getBaseComments(Long postId, int offset, int count) {
        return commentRepository.getByPostID(postId, pg(offset, count))
                    .stream()
                    .peek((com)-> { 
                        int likes = commentRepository.countLikes(com.getCommentId());
                        com.setLikes(likes);
                     })
                    .toList();
    }

    @Override
    public List<Comment> getChildComments(Long parentId, int offset, int count) {
        return commentRepository.getByParentComment(parentId, pg(offset, count))
                    .stream()
                    .peek((com)-> { 
                        int likes = commentRepository.countLikes(com.getCommentId());
                        com.setLikes(likes);
                     })
                     .toList();
    }

    Pageable pg(int offset, int count) { return PageRequest.of(offset - 1, count); }
}
