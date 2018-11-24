package com.bookshelf.dao;

import com.bookshelf.entity.Comment;

public interface CommentDAO {
    void saveComment(long isbn, String username, String comment);
    void deleteComment(int id);
}
