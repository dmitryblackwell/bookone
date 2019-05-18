package com.blackwell.dao.impl;

import com.blackwell.dao.UserDAO;
import com.blackwell.entity.Authority;
import com.blackwell.entity.Comment;
import com.blackwell.entity.Order;
import com.blackwell.entity.User;
import com.blackwell.util.BeanUtils;
import com.blackwell.util.OrderNoGenerator;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.annotation.PostConstruct;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class UserDAOMock implements UserDAO {

    private List<User> users = new ArrayList<>();

    @PostConstruct
    private void postConstruct() {
        User herasim = User.builder()
                .username("herasim")
                .email("herasim@mail.com")
                .enabled(true)
                .orders(new HashSet<>())
                .comments(new HashSet<>())
                .build();
        Authority herasimUserAuth = new Authority(herasim, "ROLE_USER");
        herasim.setAuthorities(Collections.singleton(herasimUserAuth));

        User mumu = User.builder()
                .username("mumu")
                .email("mumu@mu.mu")
                .enabled(true)
                .orders(new HashSet<>())
                .comments(new HashSet<>())
                .build();
        Authority mumuUserAuth = new Authority(mumu, "ROLE_USER");
        Authority mumuAdminAuth = new Authority(mumu, "ROLE_ADMIN");
        Set<Authority> mumuAuthorities = new HashSet<>();
        mumuAuthorities.add(mumuUserAuth);
        mumuAuthorities.add(mumuAdminAuth);
        mumu.setAuthorities(mumuAuthorities);

        users.add(herasim);
        users.add(mumu);
    }

    @Override
    public List<User> get() {
        return users;
    }

    @Override
    public User get(int id) {
        throw new NotImplementedException();
    }

    @Override
    public User get(String login) {
        List<User> filteredUsers = users.stream()
                .filter(user -> StringUtils.equals(user.getUsername(), login))
                .collect(Collectors.toList());

        return CollectionUtils.isEmpty(filteredUsers) ? null : filteredUsers.get(0);
    }

    @Override
    public void save(User user) {
        User currentUser = null;
        for (User u : users) {
            if (u.equals(user))
                currentUser = u;
            updateOrders(u.getOrders());
        }
        if (currentUser == null)
            return;

        BeanUtils.copy(currentUser, user);
    }

    @Override
    public void delete(int id) {
        throw new NotImplementedException();
    }

    private void updateOrders(Set<Order> orders) {
        for (Order order : orders) {
            if (StringUtils.isBlank(order.getOrderNo())) {
                order.setOrderNo(OrderNoGenerator.generate());
            }
        }
    }

}
