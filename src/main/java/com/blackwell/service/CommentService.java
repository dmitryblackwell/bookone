package com.blackwell.service;

import com.blackwell.entity.Comment;

import java.util.List;

public interface CommentService {
    void saveComment(Comment comment);
    // TODO add header
    void saveComment(long isbn, String username, String body);
    void deleteComment(int id);
    List<Comment> getComments(String username);
    List<Comment> getComments(long isbn);

    Double getAvgScoreByIsbn(long isbn);
}
