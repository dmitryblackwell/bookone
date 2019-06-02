package com.blackwell.util;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;

@Service
public class BeanUtils extends BeanUtilsBean {

    private static final BeanUtils instance = new BeanUtils();
    
    public static <T> void copy(T dest, T orig) {
        try {
            instance.copyProperties(dest, orig);
        } catch (IllegalAccessException | InvocationTargetException  e) {
            e.printStackTrace();
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
