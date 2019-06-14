package com.blackwell.web;

import com.blackwell.constant.PageConstants;
import com.blackwell.converter.BookToDTOConverter;
import com.blackwell.entity.Book;
import com.blackwell.entity.Comment;
import com.blackwell.model.BookDTO;
import com.blackwell.util.ServiceUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.hasItemInArray;
import static org.hamcrest.Matchers.hasProperty;
import static org.junit.Assert.*;
import static com.blackwell.MockEntityGenerator.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class BookIntegrationTest extends IntegrationTest {

    @Autowired
    private BookController bookController;

    @Autowired
    private BookToDTOConverter bookConverter;

    @Test
    public void getBooksTest() throws Exception {
        mockMvc.perform(get("/book"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attribute("searchValue", ""))
                .andExpect(model().attribute("loadBooks", true))
                .andExpect(model().attribute("bestBooks", notNullValue()));
    }

    @Test
    public void loadBooksByPageTest() throws Exception {
        mockMvc.perform(get("/book/ajax/load")
            .param("pageNo", "0"))
                .andExpect(status().isOk())
                .andExpect(view().name("snippets/book-table"))
                .andExpect(model().attribute("books", notNullValue()))
                .andExpect(model().attribute("currentPage", 0))
                .andExpect(model().attribute("pagesCount", (int) bookRepository.count()/10))
                .andExpect(model().attribute("sortColumn", "isbn"));
    }

    @Test
    public void getBooksTableByGenresNamesTest() throws Exception {
        MvcResult result = mockMvc.perform(get("/book/ajax/genre")
            .param("genresNames[]", "sci-fi,mystery"))
                .andExpect(status().isOk())
                .andExpect(view().name("snippets/book-table"))
                .andReturn();
        List<BookDTO> books = (List<BookDTO>) result.getModelAndView().getModel().get("books");
        assertTrue(books.stream()
                .allMatch(bookDTO -> bookDTO.getGenres().contains("sci-fi") &&
                                    bookDTO.getGenres().contains("mystery")));
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
    public void getEditBookTest() {
        Book generatedBook = generateBook();
        long isbn = generatedBook.getIsbn();

        bookRepository.save(generatedBook);
        ModelAndView modelAndView = bookController.getBook(isbn, true);
        assertTrue(modelAndView.getModel().get("book") instanceof Book);

        Book book = (Book) modelAndView.getModel().get("book");
        assertEquals(isbn, book.getIsbn());
        assertEquals(generatedBook, book);

        bookRepository.deleteBookByIsbn(isbn);
        assertNull(bookRepository.findById(isbn).orElse(null));
    }

    @Test
    public void getNewEditBookTest() {
        ModelAndView modelAndView = bookController.getBook(0, true);

        assertTrue(modelAndView.getModel().get("book") instanceof Book);
        Book book = (Book) modelAndView.getModel().get("book");
        assertEquals(book.getIsbn(), 0);
        assertTrue(StringUtils.isBlank(book.getName()));
        assertEquals(modelAndView.getModel().get("genres"),
                ServiceUtils.getListFromIterable(genreRepository.findAll()));
    }

    @Test
    public void saveAndDeleteCommentTest() {
        bookRepository.save(generateBook());
        userRepository.save(generateUser());

        Comment comment = Comment.builder()
                .isbn(ISBN)
                .username(USERNAME)
                .body(COMMENT)
                .build();
        ModelAndView modelAndView = bookController.saveComment(ISBN, comment);
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
