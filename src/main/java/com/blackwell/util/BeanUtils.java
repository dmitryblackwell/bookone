package com.blackwell.util;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;

@Service
public class BeanUtils extends BeanUtilsBean {

    private static final Logger LOGGER = Logger.getLogger(BeanUtils.class);
    
    private static final BeanUtils instance = new BeanUtils();
    
    public static <T> void copy(T dest, T orig) {
        try {
            instance.copyProperties(dest, orig);
        } catch (IllegalAccessException | InvocationTargetException  e) {
            LOGGER.error("Exception occurred while coping beans.", e);
        }
    }

    private BeanUtils() {}

    @Override
    public void copyProperty(Object dest, String name, Object value)
            throws IllegalAccessException, InvocationTargetException {
        if(value==null)
            return;
        super.copyProperty(dest, name, value);
    }

}
