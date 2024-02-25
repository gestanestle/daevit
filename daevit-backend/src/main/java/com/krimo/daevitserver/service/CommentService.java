package com.krimo.daevitserver.service;

import com.krimo.daevitserver.model.Comment;
import com.krimo.daevitserver.repository.CommentRepository;
import com.krimo.daevitserver.utils.Pg;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import java.util.List;

public interface CommentService {

    Comment saveComment(Comment comment);
    void deleteComment(Long id);
    Comment getComment(Long id);
    List<Comment> getBaseComments(Long postId, int offset, int count);
    List<Comment> getChildComments(Long parentId, int offset, int count);
    List<Comment> getCommentsBy(String username, int offset, int count);
}

@Service
@RequiredArgsConstructor
class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

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
        return commentRepository.getByPostID(postId, Pg.find(offset, count))
                    .stream().peek((com)-> setLikes(com)).toList();
    }

    @Override
    public List<Comment> getChildComments(Long parentId, int offset, int count) {
        return commentRepository.getByParentComment(parentId, Pg.find(offset, count))
                    .stream().peek((com)-> setLikes(com)).toList();
    }

    @Override
    public List<Comment> getCommentsBy(String username, int offset, int count) {
        return commentRepository.getCommentsBy(username, Pg.find(offset, count))
                    .stream().peek((com)-> setLikes(com)).toList();
    }

    private Comment setLikes(Comment com) { 
        int likes = commentRepository.countLikes(com.getCommentId());
        com.setLikes(likes);
        return com;
     }

}
