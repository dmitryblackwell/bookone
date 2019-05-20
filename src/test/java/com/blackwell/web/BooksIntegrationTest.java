package com.blackwell.web;

import static org.junit.Assert.*;

import com.blackwell.constant.PageConstants;
import com.blackwell.dao.*;
import com.blackwell.entity.Book;
import com.blackwell.entity.Comment;
import com.blackwell.entity.User;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(BooksController.class)
public class BooksIntegrationTest {

    private static final long ISBN = 1234L;
    private static final String NAME = "BookName";
    private static final String USERNAME = "username";
    private static final String COMMENT = "This is some Comment!";

    @Autowired
    private BooksController booksController;

    @Autowired
    @Qualifier("bookDAOMock")
    private BookDAO bookDAO;

    @Autowired
    @Qualifier("genreDAOMock")
    private GenreDAO genreDAO;

    @Autowired
    @Qualifier("commentDAOMock")
    private CommentDAO commentDAO;

    @Autowired
    @Qualifier("userDAOMock")
    private UserDAO userDAO;


    @Test
    public void getBooksTest() {
        Map<String, Object> model = booksController.getBooks().getModel();

        assertEquals(model.get("books"), bookDAO.get());
        assertEquals(model.get("genres"), genreDAO.get());
        assertTrue(model.get("book") instanceof Book);
    }

    @Test
    public void saveAndDeleteBookTest() {
        ModelAndView modelAndView = booksController.saveBook(generateBook());
        assertEquals(modelAndView.getViewName(), PageConstants.REDIRECT_HOME);

        Book book = bookDAO.get(ISBN);
        assertEquals(book, generateBook());
        assertEquals(book.getName(), NAME);

        booksController.delete(ISBN);
        assertNull(bookDAO.get(ISBN));
    }

    @Test
    public void getBookTest(){
        bookDAO.save(generateBook());
        ModelAndView modelAndView = booksController.getBook(ISBN);
        assertEquals(modelAndView.getViewName(), "bookview");

        Object bookObj = modelAndView.getModel().get("book");
        assertTrue(bookObj instanceof Book);
        Book book = (Book) bookObj;

        assertEquals(book, generateBook());
        assertEquals(book.getName(), NAME);
    }

    @Test
    public void saveAndDeleteCommentTest() {
        bookDAO.save(generateBook());
        userDAO.save(generateUser());

        ModelAndView modelAndView = booksController.saveComment(ISBN, USERNAME, COMMENT);
        final String viewName = PageConstants.REDIRECT_BOOKS + "/" +  ISBN;
        assertEquals(modelAndView.getViewName(), viewName);

        Comment userComment = getCommentFromMockUser();
        assertNotNull(userComment);

        Comment bookComment = getCommentFromMockBook();
        assertNotNull(bookComment);
        assertEquals(userComment, bookComment);

        int commentId = userComment.getId();
        booksController.deleteComment(commentId);
        assertNull(getCommentFromMockUser());
        assertNull(getCommentFromMockBook());

        bookDAO.delete(ISBN);
    }

    private Comment getCommentFromMockUser() {
        return getCommentFromList(userDAO.get(USERNAME).getComments());
    }

    private Comment getCommentFromMockBook() {
        return getCommentFromList(bookDAO.get(ISBN).getComments());
    }

    private Comment getCommentFromList(Set<Comment> comments) {
        List<Comment> filteredComments = comments.stream()
                .filter(this::isCommentEqualsToMock)
                .collect(Collectors.toList());
        if (CollectionUtils.isEmpty(filteredComments))
            return null;
        return filteredComments.get(0);
    }



    private boolean isCommentEqualsToMock(Comment comment) {
        return StringUtils.equals(comment.getComment(), COMMENT) &&
                comment.getBook().getIsbn() == ISBN &&
                StringUtils.equals(comment.getUser().getUsername(), USERNAME);
    }

    private Book generateBook() {
        return  Book.builder()
                .isbn(ISBN)
                .name(NAME)
                .comments(new HashSet<>())
                .build();
    }

    private User generateUser() {
        return User.builder()
                .username(USERNAME)
                .comments(new HashSet<>())
                .orders(new HashSet<>())
                .build();
    }

}
