package com.blackwell.web;

import com.blackwell.constant.PageConstants;
import com.blackwell.entity.Book;
import com.blackwell.entity.Comment;
import com.blackwell.util.ServiceUtils;
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

        assertEquals(model.get("books"), ServiceUtils.getListFromIterable(bookRepository.findAll()));
        assertEquals(model.get("genres"),ServiceUtils.getListFromIterable(genreRepository.findAll()));
        assertTrue(model.get("book") instanceof Book);
    }

    @Test
    public void saveAndDeleteBookTest() {
        Book generatedBook = generateBook();
        long isbn = generatedBook.getIsbn();
        ModelAndView modelAndView = bookController.saveBook(generatedBook);
        assertEquals(modelAndView.getViewName(), PageConstants.REDIRECT_HOME);

        Book book = bookRepository.findById(isbn).get();
        assertEquals(book, generatedBook);
        assertEquals(book.getName(), generatedBook.getName());

        bookController.delete(isbn);
        assertNull(bookRepository.findById(isbn).orElse(null));
    }

    @Test
    public void getBookTest(){
        Book generatedBook = generateBook();
        long isbn = generatedBook.getIsbn();

        bookRepository.save(generatedBook);
        ModelAndView modelAndView = bookController.getBook(isbn, false);
        assertEquals(modelAndView.getViewName(), "bookview");

        Object bookObj = modelAndView.getModel().get("book");
        assertTrue(bookObj instanceof Book);
        Book book = (Book) bookObj;

        assertEquals(book, generatedBook);
        assertEquals(book.getName(), generatedBook.getName());
        bookRepository.deleteBookByIsbn(isbn);
        assertNull(bookRepository.findById(isbn).orElse(null));
    }

    @Test
    public void saveAndDeleteCommentTest() {
        bookRepository.save(generateBook());
        userRepository.save(generateUser());

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

        bookRepository.deleteBookByIsbn(ISBN);
        userRepository.deleteUserByUsername(USERNAME);
    }

    private Comment getCommentFromMockUser() {
        return getMockedCommentFromList(commentRepository.findAllByUsername(USERNAME));
    }

    private Comment getCommentFromMockBook() {
        return getMockedCommentFromList(commentRepository.findAllByIsbn(ISBN));
    }

    private Comment getMockedCommentFromList(List<Comment> comments) {
        List<Comment> filteredComments = comments.stream()
                .filter(this::isCommentEqualsToMock)
                .collect(Collectors.toList());
        if (CollectionUtils.isEmpty(filteredComments))
            return null;
        return filteredComments.get(0);
    }

    private boolean isCommentEqualsToMock(Comment comment) {
        return StringUtils.equals(comment.getBody(), COMMENT) &&
                comment.getIsbn() == ISBN &&
                StringUtils.equals(comment.getUsername(), USERNAME);
    }

}
