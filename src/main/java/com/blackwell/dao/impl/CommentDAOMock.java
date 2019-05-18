package com.blackwell.dao.impl;

import com.blackwell.dao.BookDAO;
import com.blackwell.dao.CommentDAO;
import com.blackwell.dao.UserDAO;
import com.blackwell.entity.Book;
import com.blackwell.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Repository
public class CommentDAOMock implements CommentDAO {

    private static int maxCommentId = 0;

    private final BookDAO bookDAO;

    private final UserDAO userDAO;

    @Autowired
    public CommentDAOMock(@Qualifier("bookDAOMock") BookDAO bookDAO,
                          @Qualifier("userDAOMock") UserDAO userDAO) {
        this.bookDAO = bookDAO;
        this.userDAO = userDAO;
    }

    @Override
    public void save(long isbn, String username, String comment) {
        Book book = bookDAO.get(isbn);
        Comment usersComment = Comment.builder()
                        .id(maxCommentId++)
                        .book(book)
                        .user(userDAO.get(username))
                        .comment(comment)
                        .build();
        book.getComments().add(usersComment);
        userDAO.get(username).getComments().add(usersComment);
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
                filterCommentsList(book.getComments(), id)
        ));
        userDAO.get().forEach(user -> user.setComments(
                filterCommentsList(user.getComments(), id)
        ));
    }

    private Set<Comment> filterCommentsList(Set<Comment> comments, int id) {
        return comments.stream()
                .filter(comment -> comment.getId() != id)
                .collect(Collectors.toSet());
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
