package com.blackwell.dao;

import com.blackwell.entity.Comment;

public interface CommentDAO extends DAO<Comment> {
    void save(long isbn, String username, String comment);
}
