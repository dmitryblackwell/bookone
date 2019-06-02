package com.blackwell.web;

import com.blackwell.entity.Order;
import com.blackwell.repository.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

import static com.blackwell.MockEntityGenerator.ISBN;
import static com.blackwell.MockEntityGenerator.USERNAME;
import static org.junit.Assert.assertFalse;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public abstract class IntegrationTest implements InitializingBean {

    @Autowired
    WebApplicationContext wac;

    MockMvc mockMvc;
    @Autowired
    UserRepository userRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    GenreRepository genreRepository;
    @Autowired
    CommentRepository commentRepository;

    @Override
    public void afterPropertiesSet() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    Order getMockedOrderFromList(List<Order> orders) {
        List<Order> filteredOrders = orders.stream()
                .filter(this::isOrderEqualsToMock)
                .collect(Collectors.toList());
        assertFalse(CollectionUtils.isEmpty(filteredOrders));
        return filteredOrders.get(0);
    }

    private boolean isOrderEqualsToMock(Order order) {
        return order != null && order.getBook() != null && order.getBook().getIsbn() == ISBN &&
                order.getUser() != null && StringUtils.equals(order.getUser().getUsername(), USERNAME);
    }

}

