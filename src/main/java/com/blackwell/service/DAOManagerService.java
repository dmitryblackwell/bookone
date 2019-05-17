package com.blackwell.service;


import com.blackwell.dao.DAO;

public interface DAOManagerService {
    <T extends DAO> T getDAO(Class<T> clazz);
}
