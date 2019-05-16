package com.blackwell.dao;

import com.blackwell.entity.Comment;

public interface CommentDAO {
    void saveComment(long isbn, String username, String comment);
    void deleteComment(int id);
}
