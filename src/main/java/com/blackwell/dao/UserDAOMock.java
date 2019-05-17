package com.blackwell.dao;

import com.blackwell.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class UserDAOMock implements UserDAO {

    private List<User> users = new ArrayList<>();

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
        return users.stream()
                .filter(user -> StringUtils.equals(user.getUsername(), login))
                .collect(Collectors.toList())
                .get(0);
    }

    @Override
    public void save(User user) {
        // TODO implement replacement of not-null fields
        users.remove(user);
        users.add(user);
    }

    @Override
    public void delete(int id) {
        throw new NotImplementedException();
    }
}
