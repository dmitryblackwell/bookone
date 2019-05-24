package com.blackwell.service.impl;

import com.blackwell.dao.DAO;
import com.blackwell.service.DAOManagerService;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.reflections.Reflections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Service
public class DAOManagerServiceImpl implements DAOManagerService {

    private static final String MOCK = "mock";
    private static final String IMPL = "impl";

    private Map<Class<? extends DAO>, DAO> DAOs = new HashMap<>();

    @Value("${mocks.enabled}")
    private boolean isMocksEnabled;

    private final ApplicationContext applicationContext;

    @Autowired
    public DAOManagerServiceImpl(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @PostConstruct
    private void postConstruct() {
        Reflections reflections = new Reflections("com.blackwell.dao");
        Set<Class<? extends DAO>> classes = reflections.getSubTypesOf(DAO.class);
        for (Class<? extends DAO> DAOClass : classes) {
            if(StringUtils.containsIgnoreCase(DAOClass.getSimpleName(), isMocksEnabled ? MOCK : IMPL)) {
                DAOs.put((Class<? extends DAO>) DAOClass.getInterfaces()[0],
                        (DAO) getBean(DAOClass));
            }
        }
    }

    @Override
    public <T extends DAO> T getDAO(Class<T> clazz) {
        if (clazz.isInterface()) {
            return (T) DAOs.get(clazz);
        }
        return (T) getBean(clazz);
    }

    private Object getBean(Class<?> clazz) {
        String className = clazz.getSimpleName();
        className = className.substring(0, 1).toLowerCase()  + className.substring(1);
        return applicationContext.getBean(className);
    }
}
