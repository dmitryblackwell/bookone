package com.blackwell.dao.impl;

import com.blackwell.MockEntityGenerator;
import com.blackwell.dao.BookDAO;
import com.blackwell.entity.Book;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class BookDAOMockTest implements InitializingBean {

    private static final String NEW_NAME = "NewName";

    @Autowired
    @Qualifier("bookDAOMock")
    BookDAO bookDAO;

    @Override
    public void afterPropertiesSet() throws Exception {
        assertNotNull(bookDAO);
    }

    @Test
    public void CRUDTest() {
        // get all
        List<Book> startList = new ArrayList<>(bookDAO.get());
        assertTrue(CollectionUtils.isNotEmpty(startList));

        // save new book
        Book book = MockEntityGenerator.generateBook();
        bookDAO.save(book);
        assertEquals(bookDAO.get(book.getIsbn()), book);

        // save existing book
        Book copyBook = Book.builder()
                .isbn(book.getIsbn())
                .name(NEW_NAME)
                .build();
        bookDAO.save(copyBook);
        assertEquals(bookDAO.get(book.getIsbn()).getName(), NEW_NAME);

        // delete
        bookDAO.delete(book.getIsbn());
        assertNull(bookDAO.get(book.getIsbn()));

        assertEquals(startList, bookDAO.get());
    }
}
