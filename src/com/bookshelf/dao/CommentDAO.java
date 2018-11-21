package com.bookshelf.dao;

import com.bookshelf.entity.Comment;

public interface CommentDAO {
    void saveComment(Comment comment);
    void deleteComment(int id);
}
