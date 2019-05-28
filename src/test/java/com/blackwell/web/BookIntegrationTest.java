package com.blackwell.web;

import com.blackwell.constant.PageConstants;
import com.blackwell.entity.Book;
import com.blackwell.entity.Comment;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.Assert.*;
import static com.blackwell.MockEntityGenerator.*;

public class BookIntegrationTest extends IntegrationTest {

    @Autowired
    private BookController bookController;

    @Test
    public void getBooksTest() {
        Map<String, Object> model = bookController.getBooks().getModel();

        assertEquals(model.get("books"), bookDAO.get());
        assertEquals(model.get("genres"), genreDAO.get());
        assertTrue(model.get("book") instanceof Book);
    }

    @Test
    public void saveAndDeleteBookTest() {
        Book generatedBook = generateBook();
        long isbn = generatedBook.getIsbn();
        ModelAndView modelAndView = bookController.saveBook(generatedBook);
        assertEquals(modelAndView.getViewName(), PageConstants.REDIRECT_HOME);

        Book book = bookDAO.get(isbn);
        assertEquals(book, generatedBook);
        assertEquals(book.getName(), generatedBook.getName());

        bookController.delete(isbn);
        assertNull(bookDAO.get(isbn));
    }

    @Test
    public void getBookTest(){
        Book generatedBook = generateBook();
        long isbn = generatedBook.getIsbn();

        bookDAO.save(generatedBook);
        ModelAndView modelAndView = bookController.getBook(isbn);
        assertEquals(modelAndView.getViewName(), "bookview");

        Object bookObj = modelAndView.getModel().get("book");
        assertTrue(bookObj instanceof Book);
        Book book = (Book) bookObj;

        assertEquals(book, generatedBook);
        assertEquals(book.getName(), generatedBook.getName());
        bookDAO.delete(isbn);
        assertNull(bookDAO.get(isbn));
    }

    @Test
    public void saveAndDeleteCommentTest() {
        bookDAO.save(generateBook());
        userDAO.save(generateUser());

        ModelAndView modelAndView = bookController.saveComment(ISBN, USERNAME, COMMENT);
        final String viewName = PageConstants.REDIRECT_BOOKS + "/" +  ISBN;
        assertEquals(modelAndView.getViewName(), viewName);

        Comment userComment = getCommentFromMockUser();
        assertNotNull(userComment);

        Comment bookComment = getCommentFromMockBook();
        assertNotNull(bookComment);
        assertEquals(userComment, bookComment);

        int commentId = userComment.getId();
        bookController.deleteComment(commentId);
        assertNull(getCommentFromMockUser());
        assertNull(getCommentFromMockBook());

        bookDAO.delete(ISBN);
        userDAO.delete(USERNAME);
    }

    private Comment getCommentFromMockUser() {
        return getMockedCommentFromList(userDAO.get(USERNAME).getComments());
    }

    private Comment getCommentFromMockBook() {
        return getMockedCommentFromList(bookDAO.get(ISBN).getComments());
    }

    private Comment getMockedCommentFromList(Set<Comment> comments) {
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

}
