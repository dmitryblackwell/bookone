package com.blackwell.dao.impl;

import com.blackwell.MockEntityGenerator;
import com.blackwell.dao.UserDAO;
import com.blackwell.entity.Book;
import com.blackwell.entity.User;
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
import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class UserDAOMockTest implements InitializingBean {

    private static final String NEW_EMAIL = "NewEmail@email.com";

    @Autowired
    @Qualifier("userDAOMock")
    private UserDAO userDAO;

    @Override
    public void afterPropertiesSet() throws Exception {
        assertNotNull(userDAO);
    }

    @Test
    public void CRUDTest() {
        // get all
        List<User> startList = new ArrayList<>(userDAO.get());
        assertTrue(CollectionUtils.isNotEmpty(startList));

        // save new book
        User user = MockEntityGenerator.generateUser();
        userDAO.save(user);
        assertEquals(userDAO.get(user.getUsername()), user);

        // save existing book
        User copyUser = User.builder()
                .username(user.getUsername())
                .email(NEW_EMAIL)
                .build();
        userDAO.save(copyUser);
        assertEquals(userDAO.get(user.getUsername()).getEmail(), NEW_EMAIL);

        // delete
        userDAO.delete(user.getUsername());
        assertNull(userDAO.get(user.getUsername()));

        assertEquals(startList, userDAO.get());
    }

}
