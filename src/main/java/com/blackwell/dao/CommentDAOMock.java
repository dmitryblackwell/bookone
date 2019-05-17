package com.blackwell.dao;

import com.blackwell.entity.Book;
import com.blackwell.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;
import java.util.stream.Collectors;


@Repository
public class CommentDAOMock implements CommentDAO {

    private static int maxCommentId = 0;

    @Autowired
    @Qualifier("bookDAOMock")
    private BookDAO bookDAO;

    @Autowired
    @Qualifier("userDAOMock")
    private UserDAO userDAO;

    @Override
    public void save(long isbn, String username, String comment) {
        Book book = bookDAO.get(isbn);
        book.getComments().add(
                Comment.builder()
                        .id(maxCommentId++)
                        .book(book)
                        .user(userDAO.get(username))
                        .comment(comment)
                        .build());
    }

    @Override
    public void save(Comment comment) {
        this.save(comment.getBook().getIsbn(),
                comment.getUser().getUsername(),
                comment.getComment());
    }

    @Override
    public void delete(int id) {
        bookDAO.get().forEach(book -> book.setComments(
                                    book.getComments().stream()
                                        .filter(comment -> comment.getId() != id)
                                        .collect(Collectors.toSet())
                                    ));
    }

    @Override
    public List<Comment> get() {
        throw new NotImplementedException();
    }

    @Override
    public Comment get(int id) {
        throw new NotImplementedException();
    }
    
}
